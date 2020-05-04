import requests
from random import randint

for i in range(10000):
    resp = requests.delete("http://localhost:8080/v1/stock/deleteStockeById?idStock=" + str(randint(1, 500001)))
    print(resp.text)
    print("Stock _id : " + str(i) + " was deleted")