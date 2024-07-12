
# OTE - Olx To Excel generator

This is an simply Java program using Selenium and Excel dependencies. It navigates to www.olx.pl site and finds a certain product in selected localization. When it finds all products connected to this search phrase, it saves main information regarding all founded products on first page into Excel file

## Installation

If You want to try this program, please clone main branch and run it using configuration that You want to try.

```bash
Run this program using configuration with all the arguments you want to pass:

All arguments available
{"-p", "--phrase"}, description = "Phrase to be found", required = true)
{"-l", "--location"}, description = "Location to search in", required = true
{"-e", "--elements"}, description = "Number of elements to include in excel file", defaultValue = "5"
{"-f", "--pricefrom"}, description = "Starting price", defaultValue = "1"
{"-t", "--priceto"}, description = "Maximum price", defaultValue = "1000000"

Example of configuration:
-p "Rower górski" -l "Żory" -e 10
```

It means that the searching phrase is "Rower Górski", it will be searched in location "Żory" and the numbers of elements to be included in excel is set to 10. Also starting price is 1zł and ending price is 1000000zł
