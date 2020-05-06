# encoding: utf-8
import  json

import datetime
import pymongo
from random import randrange, randint

PATH = "C:\\Users\guill\Documents\FormationJavaData\BIG_DATA_PROJET\microservice-stock\datasetes"

class Stock:
    def __init__(self, _id):
        self._id = _id
        self.magasin = "MAG" + str(randint(1,10))
        self.quantite = randint(0, 1000)
        self.active = bool(randint(0,1))
        self.idProduit = randint(0, 100000)
        self.creationDate = self.random_date().strftime('%Y-%m-%dT%H:%M:%S.000+0000')

    def random_date(self):
        start = datetime.datetime(randint(1980, datetime.datetime.now().year),
                                  randint(1, 12),
                                  1, 0, 0, 0)
        res = start + datetime.timedelta(days=randrange(30),
                                         hours=randrange(24),
                                         minutes=randrange(60),
                                         seconds=randrange(60))
        return res

    def __str__(self):
        return "stock : [" + str(self.idStock) \
               + ", " + str(self.magasin) \
               + ", " + str(self.quantite) \
               + ", " + str(self.active) \
               + ", " + str(self.idProduit) \
               + ", " + str(self.creationDate) + "]"

for i in range(1,6):
    lstObj = [Stock(j).__dict__ for j in range(1, 100000)]
    print(json.dumps(lstObj, indent=4))
    with open(PATH + "\stock" + str(i) + ".json", "w") as out:
        json.dump(lstObj, out, indent=4)
