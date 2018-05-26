import struct
import numpy as np
from sklearn import manifold
import matplotlib.pyplot as plt
import csv

# open the dataset file
with open('mnist-digit-9-5000-obs.csv', 'r') as f:
    reader = csv.reader(f)
    X = list(reader)

#print(dataset)
#print("hi from python")

# perform ISOMap
Y = manifold.Isomap(n_neighbors=5, n_components=2).fit_transform(X)

print(Y)
