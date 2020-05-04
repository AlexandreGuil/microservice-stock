import requests
from random import randint

for i in range(10000):
    try:
        index = str(randint(1, 500001))
        resp = requests.delete("http://localhost:8080/v1/stock/deleteStockeById?idStock=" + index)
        print(resp.text)
        print("Stock _id : " + index + " was deleted")
    except:
        continue
        