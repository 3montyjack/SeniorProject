
import os.path
import os

outPath = "./ImageProcessing/PreProcessedImages/"
finalPath = "./ImageProcessing/FinalImagesForTesting/"

path, dirs, files = next(os.walk(outPath))
file_count = len(files)

print("Pre Processed Images: " + str(file_count))

path, dirs, files = next(os.walk(finalPath))
new_file_count = 0
for dir in dirs:
    inside_path, indside_dirs, inside_files = next(os.walk(finalPath + dir))
    for dir2 in indside_dirs:
        inside_inside_path, inside_indside_dirs, inside_inside_files = next(os.walk(finalPath + "/" + dir + "/" + dir2))
        
        new_file_count = new_file_count + len(inside_inside_files)
new_file_count = new_file_count + len(files)

print("Post Processed Images: " + str(new_file_count))

