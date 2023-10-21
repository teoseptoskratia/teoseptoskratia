# https://teoseptoskratia.com

## Requirements

JDK 1.8

## Usage

You should `lein npm install` before doing anything to ensure you get the `source-map-support` library.

To build the clojurescript into a node script, use `lein build && lein resource`.

To then run the node app use `node build/server/main.js`.

You can add clojure and clojurescript dependencies in the normal `:dependency` spot.
You can also add `npm` dependencies in the `:npm {:dependency []}` spot.

![Alt Start](img/1.Start.png "Start")

![Alt Admin](img/2.Admin.png "Admin")

![Alt Create_Customer](img/3.Create_Customer.png "Create Customer")

![Alt Customer](img/4.Customer.png "Customer")

![Alt Wallets](img/5.Wallets.png "Wallets")

![Alt DEX](img/6.DEX.png "DEX")
