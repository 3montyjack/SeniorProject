

import urllib.request
import os.path
import os

path = "./ImageProcessing/URLSFORDOWNLOAD"
outPath = "./ImageProcessing/PreProcessedImages/"

path, something, files = next(os.walk(path))
count = 0
for tempFile in files:
    tempFile = open(path + "/" + tempFile, mode='r',
                    encoding='utf8', errors='ignore')
    for line in tempFile.readlines():
        imagePath = line.split("\t")[0]
        try:
            # print (imagePath)
            # if not path.isfile(outPath + imagePath.split("/")[-1]):
            urllib.request.urlretrieve(
                imagePath, outPath + imagePath.split("/")[-1])
            # print(imagePath)

        except Exception as e:
            print("Error with something" + str(count))
            print(e)
            count = count-1
        count = count + 1
        if count is 31000:
            break
print(count)
