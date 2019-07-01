/*Import data:
        mongoimport --db test
                    --collection restaurants
                    --drop
                    --file primer-dataset.json
Start shell:
        mongo
*/

//Select the test DB

use test;

// INSERT

db.restaurants.insert(
   {
      "address" : {
         "street" : "2 Avenue",
         "zipcode" : "10075",
         "building" : "1480",
         "coord" : [ -73.9557413, 40.7720266 ],
      },
      "borough" : "Manhattan",
      "cuisine" : "Italian",
      "grades" : [
         {
            "date" : ISODate("2014-10-01T00:00:00Z"),
            "grade" : "A",
            "score" : 11
         },
         {
            "date" : ISODate("2014-01-16T00:00:00Z"),
            "grade" : "B",
            "score" : 17
         }
      ],
      "name" : "Vella",
      "restaurant_id" : "41704620"
   }
);

// We couls insert an array of documents with
// db.restaurants.insert([{doc1}, {doc2}, ...])

// FIND

// We can get all the restaurants of the collection with

db.restaurants.find();

// is the same as:

db.restaurants.find({});

// Search with equality restrictions

// { <field1>: <value1>, <field2>: <value2>, ... }

db.restaurants.find( { "borough": "Manhattan" } )

db.restaurants.find( { "address.zipcode": "10075" } )

db.restaurants.find( { "grades.grade": "B" } )

// Search with operators

// { <field1>: { <operator1>: <value1> } }

db.restaurants.find( { "grades.score": { $gt: 30 } } )

db.restaurants.find( { "grades.score": { $lt: 10 } } )

db.restaurants.find( { "cuisine": "Italian", "address.zipcode": "10075" } )

db.restaurants.find(
   { $or: [ { "cuisine": "Italian" },
            { "address.zipcode": "10075" } ] }
)

// Projections

db.restaurants.find( { "cuisine": "Italian" }, { "name" : 1} ) // 1 or true

db.restaurants.find( { "cuisine": "Italian" }, { "name" : 1, "_id" : 0} ) // 0 or false

db.restaurants.find( { "cuisine": "Italian" }, { "address" : 0} )

// Sorting the results

db.restaurants.find().sort( { "borough": 1, "address.zipcode": 1 } )

//The array case

// The MongoShell let that the fields inside the JSON objects do not go between " "

db.inventory.insert([
  { _id: 5, type: "food", item: "aaa", ratings: [ 5, 8, 9 ] },
  { _id: 6, type: "food", item: "bbb", ratings: [ 5, 9 ] },
  { _id: 7, type: "food", item: "ccc", ratings: [ 9, 5, 8 ] }
])

// Exact correspondency

db.inventory.find( { ratings: [ 5, 8, 9 ] } )

// All the elements

db.inventory.find( { ratings: { $all : [ 5, 8, 9] } })

// Some elements in the set

db.inventory.find( { ratings: { $in : [ 5, 7] } })

// One element

db.inventory.find( { ratings: 5 } )

// A specific element

db.inventory.find( { 'ratings.0': 5 } )

//A requirement that meets on of the elements

// A requirement that there are elements that satisfy the requirements
// For example we can satify one property with an element and another one with another element

db.inventory.find( { ratings: { $gt: 5, $lt: 9 } } )

// In this case, every array has an 8 that satisfy this requirement

db.inventory.find( { ratings: { $elemMatch: { $gt: 5, $lt: 9 } } } )

// UPDATE

// Normally, substitute is not what we want

db.inventory.update( {item : "aaa"}, {ratings: [4]})

db.inventory.find({item : "aaa"})
db.inventory.find()

// There is a set of operators used in case of updates

db.restaurants.find({ "name" : "Juni" })

// Simple Atribute

db.restaurants.update(
    { "name" : "Juni" },
    {
      $set: { "cuisine": "American (New)" },
      $currentDate: { "lastModified": true }
    }
)

// Nested document

db.restaurants.update(
  { "restaurant_id" : "41156888" },
  { $set: { "address.street": "East 31st Street" } }
)

// About multiple documents
db.restaurants.update(
  { "address.zipcode": "10016", cuisine: "Other" },
  {
    $set: { cuisine: "Category To Be Determined" },
    $currentDate: { "lastModified": true }
  },
  { multi: true}
)

// Some modification operators

// $inc

{
  _id: 1,
  sku: "abc123",
  quantity: 10,
  metrics: {
    orders: 2,
    ratings: 3.5
  }
}

db.products.update(
   { sku: "abc123" },
   { $inc: { quantity: -2, "metrics.orders": 1 } }
)

{
   "_id" : 1,
   "sku" : "abc123",
   "quantity" : 8,
   "metrics" : {
      "orders" : 3,
      "ratings" : 3.5
   }
}

// $mul

{ _id: 1, item: "ABC", price: 10.99 }

db.products.update(
   { _id: 1 },
   { $mul: { price: 1.25 } }
)

{ _id: 1, item: "ABC", price: 13.7375 }

// $rename

{ "_id": 1,
  "alias": [ "The American Cincinnatus", "The American Fabius" ],
  "mobile": "555-555-5555",
  "nmae": { "first" : "george", "last" : "washington" }
}

db.students.update( { _id: 1 }, { $rename: { "nmae": "name" } } )

{
  "_id": 1,
  "alias": [ "The American Cincinnatus", "The American Fabius" ],
  "mobile": "555-555-5555",
  "name": { "first" : "george", "last" : "washington" }
}

db.students.update( { _id: 1 }, { $rename: { "name.first": "name.fname" } } )

{
  "_id" : 1,
  "alias" : [ "The American Cincinnatus", "The American Fabius" ],
  "mobile" : "555-555-5555",
  "name" : { "fname" : "george", "last" : "washington" }
}

// Some updates on arrays

// $addToSet

{ _id: 1, letters: ["a", "b"] }

db.test.update(
   { _id: 1 },
   { $addToSet: {letters: [ "c", "d" ] } }
)

{ _id: 1, letters: [ "a", "b", [ "c", "d" ] ] }

{ _id: 1, item: "polarizing_filter", tags: [ "electronics", "camera" ] }

db.inventory.update(
   { _id: 1 },
   { $addToSet: { tags: "accessories" } }
)

db.inventory.update(
   { _id: 1 },
   { $addToSet: { tags: "camera"  } }
)

{ _id: 1, item: "polarizing_filter", tags: [ "electronics", "camera" , "accessories"] }

// $each

{ _id: 2, item: "cable", tags: [ "electronics", "supplies" ] }

db.inventory.update(
   { _id: 2 },
   { $addToSet: { tags: { $each: [ "camera", "electronics", "accessories" ] } } }
 )

 {
  _id: 2,
  item: "cable",
  tags: [ "electronics", "supplies", "camera", "accessories" ]
}

// $pop

{ _id: 1, scores: [ 8, 9, 10 ] }

db.students.update( { _id: 1 }, { $pop: { scores: -1 } } )

{ _id: 1, scores: [ 9, 10 ] }

db.students.update( { _id: 1 }, { $pop: { scores: 1 } } )

{ _id: 1, scores: [ 9 ] }

// $pullAll

{ _id: 1, scores: [ 0, 2, 5, 5, 1, 0 ] }

db.survey.update( { _id: 1 }, { $pullAll: { scores: [ 0, 5 ] } } )

{ "_id" : 1, "scores" : [ 2, 1 ] }

// $pull

{
   _id: 1,
   fruits: [ "apples", "pears", "oranges", "grapes", "bananas" ],
   vegetables: [ "carrots", "celery", "squash", "carrots" ]
}
{
   _id: 2,
   fruits: [ "plums", "kiwis", "oranges", "bananas", "apples" ],
   vegetables: [ "broccoli", "zucchini", "carrots", "onions" ]
}

db.stores.update(
    { },
    { $pull: { fruits: { $in: [ "apples", "oranges" ] },
               vegetables: "carrots" } },
    { multi: true }
)

{
  "_id" : 1,
  "fruits" : [ "pears", "grapes", "bananas" ],
  "vegetables" : [ "celery", "squash" ]
}
{
  "_id" : 2,
  "fruits" : [ "plums", "kiwis", "bananas" ],
  "vegetables" : [ "broccoli", "zucchini", "onions" ]
}

{ _id: 1, votes: [ 3, 5, 6, 7, 7, 8 ] }

db.profiles.update( { _id: 1 }, { $pull: { votes: { $gte: 6 } } } )

{ _id: 1, votes: [  3,  5 ] }

{
   _id: 1,
   results: [
      { item: "A", score: 5 },
      { item: "B", score: 8, comment: "Strongly agree" }
   ]
}
{
   _id: 2,
   results: [
      { item: "C", score: 8, comment: "Strongly agree" },
      { item: "B", score: 4 }
   ]
}

db.survey.update(
  { },
  { $pull: { results: { score: 8 , item: "B" } } },
  { multi: true }
)

{
   "_id" : 1,
   "results" : [ { "item" : "A", "score" : 5 } ]
}
{
  "_id" : 2,
  "results" : [
      { "item" : "C", "score" : 8, "comment" : "Strongly agree" },
      { "item" : "B", "score" : 4 }
   ]
}

// Remove

db.restaurants.remove( { "borough": "Manhattan" } )

db.restaurants.remove( { "borough": "Queens" }, { justOne: true } )

db.restaurants.remove( { } )

// Drop a collection

db.restaurants.drop()

// Queries performance

db.inventory.drop()

db.inventory.insert([
  { "_id" : 1, "item" : "f1", type: "food", quantity: 500 },
  { "_id" : 2, "item" : "f2", type: "food", quantity: 100 },
  { "_id" : 3, "item" : "p1", type: "paper", quantity: 200 },
  { "_id" : 4, "item" : "p2", type: "paper", quantity: 150 },
  { "_id" : 5, "item" : "f3", type: "food", quantity: 300 },
  { "_id" : 6, "item" : "t1", type: "toys", quantity: 500 },
  { "_id" : 7, "item" : "a1", type: "apparel", quantity: 250 },
  { "_id" : 8, "item" : "a2", type: "apparel", quantity: 400 },
  { "_id" : 9, "item" : "t2", type: "toys", quantity: 50 },
  { "_id" : 10, "item" : "f4", type: "food", quantity: 75 }
])

db.inventory.find(
   { quantity: { $gte: 100, $lte: 200 } }
).explain("executionStats")

db.inventory.createIndex( { quantity: 1 } )

db.inventory.find(
   { quantity: { $gte: 100, $lte: 200 } }
).explain("executionStats")

// Comparing various indices

db.inventory.find( { quantity: { $gte: 100, $lte: 300 }, type: "food" } )

db.inventory.createIndex( { quantity: 1, type: 1 } )
db.inventory.createIndex( { type: 1, quantity: 1 } )

db.inventory.find(
   { quantity: { $gte: 100, $lte: 300 }, type: "food" }
).hint({ quantity: 1, type: 1 }).explain("executionStats")

db.inventory.find(
   { quantity: { $gte: 100, $lte: 300 }, type: "food" }
).hint({ type: 1, quantity: 1 }).explain("executionStats")
