from __future__ import (absolute_import, division, print_function,
                        unicode_literals)

import pathlib
import glob
import os

import tensorflow as tf
from tensorflow.keras import datasets, layers, models

from PIL import Image
import matplotlib.pyplot as plt

import numpy as np
import IPython.display as display



os.environ['TF_CPP_MIN_LOG_LEVEL'] = '2'

BATCH_SIZE = 1000

size = [0,0,250,250,4]
  


data_dir = "./ImageProcessing/CatagorizedForCNN/training/"

train_files = tf.data.Dataset.list_files("./ImageProcessing/CatagorizedForCNN/training/*/*.png")
test_files = tf.data.Dataset.list_files("./ImageProcessing/CatagorizedForCNN/training/*/*.png")

tempCategories = [x[0] for x in os.walk(data_dir)]
categories = []
for i in tempCategories:
  categories.append(i.split('/')[-1])


def getLabel(filePath):
  partsOfPath = tf.strings.split(filePath, os.path.sep)
  index = 0
  for i in range(len(categories)):
    if partsOfPath[-2] == categories[i]:
      index = i
  
  return index
  #  == categories


def decodeImg(path):
  img = tf.io.decode_png(path, 4)
  img = tf.image.convert_image_dtype(img, tf.float32)
  return tf.image.resize_with_crop_or_pad(img,size[2],size[3])


def processPath(file_path):
  label = getLabel(file_path)
  # load the raw data from the file as a string
  img = tf.io.read_file(file_path)
  img = decodeImg(img)
  return img, label

train_datastore = train_files.map(processPath, num_parallel_calls=tf.data.experimental.AUTOTUNE)

test_datastore = test_files.map(processPath, num_parallel_calls=tf.data.experimental.AUTOTUNE)


def prepare_for_training(ds, cache=True, shuffle_buffer_size=100000):
  # This is a small dataset, only load it once, and keep it in memory.
  # use `.cache(filename)` to cache preprocessing work for datasets that don't
  # fit in memory.
  if cache:
    if isinstance(cache, str):
      ds = ds.cache(cache)
    else:
      ds = ds.cache()

  ds = ds.shuffle(buffer_size=shuffle_buffer_size)

  # Repeat forever
  ds = ds.repeat()

  ds = ds.batch(BATCH_SIZE)

  # `prefetch` lets the dataset fetch batches in the background while the model
  # is training.
  ds = ds.prefetch(buffer_size=tf.data.experimental.AUTOTUNE)

  return ds


train_datastore = prepare_for_training(train_datastore)
test_datastore = prepare_for_training(test_datastore)

train_images, train_labels = next(iter(train_datastore))
test_images, test_labels = next(iter(test_datastore))

# train_images, train_labels = train_images, train_labels

# plt.figure(figsize=(10, 10))
# for i in range(25):
#     plt.subplot(5, 5, i+1)
#     plt.xticks([])
#     plt.yticks([])
#     plt.grid(False)
#     plt.imshow(train_images[i], cmap=plt.cm.binary)
#     # The CIFAR labels happen to be arrays,
#     # which is why you need the extra index
#     plt.xlabel(categories[train_labels[i][0]])
# plt.show()
# for image, label in train_datastore.take(3):
#     print("Image shape: ", image.numpy().shape)
#     print("Label: ", label.numpy())

model = models.Sequential()
model.add(layers.Conv2D(32, (3, 3), activation='relu', input_shape=(size[2], size[3], size[4])))
model.add(layers.MaxPooling2D((2, 2)))
model.add(layers.Conv2D(64, (3, 3), activation='relu'))
model.add(layers.MaxPooling2D((2, 2)))
model.add(layers.Conv2D(64, (3, 3), activation='relu'))

model.add(layers.Flatten())
model.add(layers.Dense(64, activation='relu'))
model.add(layers.Dense(len(categories)))

# model.summary()
model.compile(optimizer='adam', loss=tf.keras.losses.SparseCategoricalCrossentropy(from_logits=True), metrics=['accuracy'])
history = model.fit(train_images, train_labels, epochs=5, validation_data=(test_images, test_labels))


plt.plot(history.history['accuracy'], label='accuracy')
plt.plot(history.history['val_accuracy'], label='val_accuracy')
plt.xlabel('Epoch')
plt.ylabel('Accuracy')
plt.ylim([0.5, 1])
plt.legend(loc='lower right')

plt.show()

# test_loss, test_acc = model.evaluate(test_images,  test_labels, verbose=2)

# print(test_acc)



"These are the no go commented code"


# plt.figure(figsize=(10,10))
# for i in range(25):
#     plt.subplot(5,5,i+1)
#     plt.xticks([])
#     plt.yticks([])
#     plt.grid(False)
#     plt.imshow(files, cmap=plt.cm.binary)
#     # The CIFAR labels happen to be arrays, 
#     # which is why you need the extra index
#     plt.xlabel(class_names[files])
# plt.show()


# foldername = "./ImageProcessing/101_ObjectCategories/101_ObjectCategories"

# dataPath = os.getcwd() + '/ImageProcessing/101_ObjectCategories/101_ObjectCategories'
# imageTemp = Image.open(dataPath + '/airplanes/image_0001.jpg').crop(IMAGE_SHAPE)
# imageTemp = np.array(imageTemp)

# print(imageTemp.shape)

# plt.imshow(imageTemp)
# # plt.show()

# image_array = np.array([imread(im) for im in glob.glob(f"{foldername}/*/*.jpg")])

# tf.keras.utils.load_data(image_array)



# data_dir = tf.keras.utils.load_data(path=dataPath)
# print(data_dir.cwd())
# image_count = len(list(data_dir.glob('*/*.jpg')))
# train_images = data_dir.glob('*/*.jpg')
# print(image_count)

# list_ds = tf.data.Dataset.list_files(str(dataPath/'*/*'))

# for f in list_ds.take(5):
#   print(f.numpy())
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




