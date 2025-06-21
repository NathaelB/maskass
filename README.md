# 🕵️‍♂️ Maskass — Le Ninja de l’anonymisation RGPD

> Parce que tes données sensibles ne devraient jamais finir dans un log, un `.csv` public, ou sur X.

---

## 🎭 Qu’est-ce que Maskass ?

**Maskass** est un mini serveur HTTP écrit en **Scala 3 + ZIO** pour anonymiser les données **par design**.  
Il ne laisse **aucune chance à l’erreur humaine**. Tu peux tout lui envoyer, il masquera ce qu’il faut.

Exemple :

```json
{
  "username": "toto",
  "email": "toto@foo.bar",
  "address": "1 Avenue des Champs-Élysées",
  "nationalId": "98491"
}
```

Réponse automatique:

```json
{
  "username": "toto",
  "email": "***",
  "address": "***",
  "nationalId": "***"
}
```


Oui, même si un développeur oublie de faire un `.copy(email = "***")`, Maskass veille.

## 🛡️ RGPD-Compliant par Construction
🧬 Maskass utilise un type Masked pour les champs sensibles.
Ce type:
- Cache sa valeur (impossible d'en extraire le contenu)
- Se sérialise toujours en `"***"`
- Protège même les développeurs fatigués du vendredi soir
Tu oublies de masquer un champ ? Pas possible, le compilateur te tape sur les doigts 🧠

## 🚀 Comment lancer le ninja

```
sbt run
```


Puis envoie-lui des données via `curl`, Postman, ou ton frontend préféré.

```
curl -X POST http://localhost:8080/anonymize \
  -H "Content-Type: application/json" \
  -d '{"username": "toto", "email": "secret@data.net", "address": "42 rue obscure", "nationalId": "XYZ123"}'
```
💥 Il retourne une version purifiée.

## 🎨 Pourquoi “Maskass” ?
Parce que :
- Les données sensibles méritent un masque
- Le projet devait avoir un nom stylé
- Et on voulait un clin d’œil à ce personnage masqué discret mais redoutable de Mario Kart 🏁

## 🔧 Stack Technique
- 🐍 Scala 3 — parce que les macros c’est magique
- ⚡ ZIO 2 — effets, fibres et pureté fonctionnelle
- 🌐 ZIO HTTP 3 — serveur web rapide et type-safe
- 🧬 Circe — pour le JSON
- 🔒 Typage fort — pour rendre l’oubli de masquage impossible