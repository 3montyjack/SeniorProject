import tensorflow_datasets.public_api as tfds


class customDataset(tfds.core.GeneratorBasedBuilder):
  """Short description of my dataset."""

  VERSION = tfds.core.Version('0.1.0')

  def _info(self):
    # Specifies the tfds.core.DatasetInfo object
    return tfds.core.DatasetInfo(
      builder=self, 
      description=("This data set is for the catagorization of encryption metods used for data hiding"),
      features=tfds.features.FeaturesDict({
        "image_description": tfds.features.Text(),
        "image" : tfds.features.Image(),
        "label" : tfds.features.ClassLabel
      }),
      supervised_keys = ("image", "label"),
      homepage="https://dataset-homepage.org",
      citation=r"""@article{my-awesome-dataset-2020, author = {Smith, John},"}"""
      )

  def _split_generators(self, dl_manager):
    # Downloads the data and defines the splits
    # dl_manager is a tfds.download.DownloadManager that can be used to
    # download and extract URLs
    extracted_path = "./ImageProcessing/101_ObjectCategories/101_ObjectCategories"

    dl_paths = dl_manager.download_and_extract({
      tfds.core.SplitGenerator(
        name=tfds.Split.Train,
        gen_kwargs={
          "images_dir_path":os.path.join(extracted_path, "train"),
          "labels" : os.path.join(extracted_path, "train_lables.csv"),
        }
      )
    })

  def _generate_examples(self, images_dir_path, labels):
      # Read the input data out of the source files
    for image_file in tf.io.gfile.listdir(images_dir_path):
      print("Here" + str(image_file))
    with tf.io.gfile.GFile(labels) as f:
      print("Here" + str(f))
      

    # And yield examples as feature dictionaries
    for image_id, description, label in data:
      yield image_id, {
          "image_description": description,
          "image": "%s/%s.jpeg" % (images_dir_path, image_id),
          "label": label,
      }
