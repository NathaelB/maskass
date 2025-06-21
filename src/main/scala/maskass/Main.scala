package maskass

import zio._
import zio.http._
import io.circe.parser._
import io.circe.syntax._
import io.circe.generic.auto._

final case class Masked private (private val value: String)

object Masked {
  def apply(value: String): Masked = new Masked(value)

  given io.circe.Encoder[Masked] = io.circe.Encoder.encodeString.contramap(_ => "***")
  given io.circe.Decoder[Masked] = io.circe.Decoder.decodeString.map(Masked(_))
}

case class Person(username: String, email: Masked, address: Masked, nationalId: Masked)

object Main extends ZIOAppDefault {
  private val routes: Routes[Any, Nothing] = Routes(
    Method.POST / "anonymize" -> handler { (req: Request) =>
      (
        for {
          body <- req.body.asString
          person <- ZIO.fromEither(decode[Person](body))
          json = person.asJson.noSpaces
        } yield Response.text(json)
      ).catchAll { err =>
        ZIO.succeed(Response.text(s"Invalid input: ${err.getMessage}").status(Status.BadRequest))
      }
    }
  )

  override def run: ZIO[Any with ZIOAppArgs with Scope, Any, Any] =
    Server.serve(routes @@ Middleware.identity).provide(Server.default)
}