Programming challenge description:
We say a portfolio matches the benchmark when the number of shares of each asset in the portfolio matches the number of shares of each asset in the benchmark. Your question is to write a program that determines the transactions necessary to make a portfolio match a benchmark.

A portfolio is a collection of assets such as stocks and bonds. A portfolio could have 10 shares of Vodafone stock, 15 shares of Google stock and 15 shares of Microsoft bonds. A benchmark is also just a collection of assets. A benchmark could have 15 shares of Vodafone stock, 10 shares of Google stock and 15 shares of Microsoft bonds.

A transaction is when you “buy” or “sell” a particular asset of certain asset type (“stock” or “bond”). For instance, you can decide to buy 5 shares of Vodafone stock which, given the portfolio described above, would result in you having 15 shares of Vodafone stock. Correspondingly, you decide to sell 5 shares of Microsoft bonds, which would result in 10 shares of Microsoft bonds in the above portfolio.

Assumptions:

* Shares are positive decimals      
* There will always be at least 1 asset present in the Portfolio and Benchmark     
* A particular asset can be bond, stock, or both. For example, 5 shares of Microsoft bonds and 10 shares of Microsoft stock can both be present in the portfolio/benchmark      
* The trades should be sorted in alphabetical order based on the names of the assets; if both bonds and stock are present for an asset, list bonds first       

####Input:        
The first part of the input is the Portfolio holdings (in the format Name,AssetType,Shares where each asset is separated by '|' symbol)       
The second part of the input is the Benchmark holdings (in the format Name,AssetType,Shares where each asset is separated by '|' symbol)       
Example input: Vodafone,STOCK,10|Google,STOCK,15|Microsoft,BOND,15:Vodafone,STOCK,15|Google,STOCK,10|Microsoft,BOND,15       

Note that the two parts are separated by the ':' symbol.

Output:
The output is a list of transactions (separated by new line) in the format TransactionType,Name,AssetType,Shares. Note that the TransactionType should only be BUY or SELL.

Example output:           
SELL,Google,STOCK,5          
BUY,Vodafone,STOCK,5         

_____________

###Test 1

##Test Input

Vodafone,STOCK,10|Google,STOCK,15:Vodafone,STOCK,15|Vodafone,BOND,10|Google,STOCK,10

##Expected Output

SELL,Google,STOCK,5      
BUY,Vodafone,BOND,10     
BUY,Vodafone,STOCK,5     

_____________

###Test 2

##Test Input

Vodafone,STOCK,10|Google,STOCK,15|Microsoft,BOND,15:Vodafone,STOCK,15|Google,STOCK,10|Microsoft,BOND,15

##Expected Output

SELL,Google,STOCK,5       
BUY,Vodafone,STOCK,5       

_____________

###Test 3

##Test Input

 Vodafone,BOND,10|Vodafone,STOCK,10|Google,STOCK,15|Microsoft,STOCK,10:Microsoft,BOND,10|Microsoft,STOCK,10|Vodafone,STOCK,15|Google,STOCK,10
##Expected Output

SELL,Google,STOCK,5  
BUY,Microsoft,BOND,10   
SELL,Vodafone,BOND,10     
BUY,Vodafone,STOCK,5     