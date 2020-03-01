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

# data_dir = open()
data_dir = pathlib.Path(
    'G:/Desktop/School/Senior%20Project/101_ObjectCategories/101_ObjectCategories')
print(data_dir.cwd())
image_count = len(list(data_dir.glob('*/*.jpg')))

print(image_count)

# plt.figure()
# plt.imshow(train_images[0])
# plt.colorbar()
# plt.grid(False)
# plt.show()
