from __future__ import (absolute_import, division, print_function,
                        unicode_literals)

import pathlib
import glob
import os

import tensorflow as tf
from tensorflow.keras import datasets, layers, models
import matplotlib.pyplot as plt

import numpy as np



os.environ['TF_CPP_MIN_LOG_LEVEL'] = '2'

BATCH_SIZE_TRAINING = 59000
BATCH_SIZE_VALIDATION = 10000

size = [0,0,250,250,4]

data_dir = "./ImageProcessing/FinalImagesForTesting/training/"

train_files = tf.data.Dataset.list_files("./ImageProcessing/FinalImagesForTesting/training/*/*.png")
test_files = tf.data.Dataset.list_files("./ImageProcessing/FinalImagesForTesting/validation/*/*.png")

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


<<<<<<< HEAD
def prepare_for_training(ds, cache=True, shuffle_buffer_size=10000):
=======
def prepare_for_training(ds, sizeOfBatch, cache=True, shuffle_buffer_size=10000):
>>>>>>> be357c153d5e29094ba69b01d0d424ca6d900cf6
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

  ds = ds.batch(sizeOfBatch)

  # `prefetch` lets the dataset fetch batches in the background while the model
  # is training.
  ds = ds.prefetch(buffer_size=tf.data.experimental.AUTOTUNE)

  return ds


train_datastore = prepare_for_training(train_datastore, BATCH_SIZE_TRAINING)
test_datastore = prepare_for_training(test_datastore, BATCH_SIZE_VALIDATION)

train_images, train_labels = next(iter(train_datastore))
test_images, test_labels = next(iter(test_datastore))


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
<<<<<<< HEAD
history = model.fit(train_images, train_labels, epochs=20, validation_data=(test_images, test_labels))
w
=======
history = model.fit(train_images, train_labels, epochs=10, validation_data=(test_images, test_labels))

>>>>>>> be357c153d5e29094ba69b01d0d424ca6d900cf6

plt.plot(history.history['accuracy'], label='accuracy')
plt.plot(history.history['val_accuracy'], label='val_accuracy')
plt.xlabel('Epoch')
plt.ylabel('Accuracy')
plt.ylim([0.5, 1])
plt.legend(loc='lower right')

plt.show()

# test_loss, test_acc = model.evaluate(test_images,  test_labels, verbose=2)

# print(test_acc)
