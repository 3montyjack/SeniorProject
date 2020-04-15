

import urllib.request

import os

path = "./ImageProcessing/URLSFORDOWNLOAD"
outPath = "./ImageProcessing/PreProcessedImages/"

path, something, files = next(os.walk(path))
for tempFile in files:
    print(tempFile)
    
    tempFile = open(path + "/" + tempFile)
    for line in tempFile.readlines():
        
        imagePath = line.split("\t")[0]
        try:
            print (imagePath)
            urllib.request.urlretrieve(imagePath, outPath + imagePath.split("/")[-1]) 
        except:
            pass
