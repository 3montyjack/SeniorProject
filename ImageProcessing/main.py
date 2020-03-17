from __future__ import (absolute_import, division, print_function,
                        unicode_literals)

import pathlib

import matplotlib.pyplot as plt
import numpy as np
import IPython.display as display
from PIL import Image
import pandas as pd
import tensorflow as tf
import os

import pathlib


dataPath = './ImageProcessing/101_ObjectCategories'
data_dir = tf.keras.utils.get_file('101_ObjectCategories', dataPath, untar=False)
print(data_dir.cwd())
image_count = len(list(data_dir.glob('*/*.jpg')))
train_images = data_dir.glob('*/*.jpg')
print(image_count)

list_ds = tf.data.Dataset.list_files(str(flowers_root/'*/*'))

for f in list_ds.take(5):
  print(f.numpy())
# dataPath = 'file://G:/Desktop/School/Senior Project/FinalProject/ImageProcessing/101_ObjectCategories/101_ObjectCategories'


# tf.data.experimental.make

# train_images = tf.keras.utils.get_file(fname=os.path.basename(dataPath),
#                                            origin=dataPath)
# print("Local copy of the dataset file: {}".format(train_images))



# train_labels = ['LeastSigBit', 'MostSigBit', 'NotEncrypted']
# index = 0
# for i in train_images:

#     plt.subplot(5,5,index + 1)
#     plt.xticks([])
#     plt.yticks([])
#     plt.grid(False)
#     plt.imshow(i, cmap=plt.cm.binary)
#     # The CIFAR labels happen to be arrays, 
#     # which is why you need the extra index
#     plt.xlabel(class_names[i[0]])
#     index = index +1
# plt.show()


# # Convolutional Neural Network
# plt.figure(figsize=(10,10))

# model = models.Sequential()
# model.add(layers.Conv2D(32, (3, 3), activation='relu', input_shape=(32, 32, 3)))
# model.add(layers.MaxPooling2D((2, 2)))
# model.add(layers.Conv2D(64, (3, 3), activation='relu'))
# model.add(layers.MaxPooling2D((2, 2)))
# model.add(layers.Conv2D(64, (3, 3), activation='relu'))


# model.add(layers.Flatten())
# model.add(layers.Dense(64, activation='relu'))
# model.add(layers.Dense(10))

# model.summary()

# plt.figure()

# plt.imshow(train_images[0])
# plt.colorbar()
# plt.grid(False)
# plt.show()
