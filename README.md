# ImageCompressor
Here, compressedImage constructors may either take in a 2D array of 0s and 1s, or a name of the file that has the
same representation written in the textfile. getPixelValue and setPixelValue are operations to access and modify
individual pixels. numberOfBlackPixels counts the number of black pixels in each row. Invert method makes each
black pixel white and each white pixel black. Boolean operations over images are defined as: for each pixel (i,j), or
returns white if any pixel is white, whereas and returns white if both pixels are white. Finally, xor returns white if
exactly one of the pixels is white. It also has two methods to convert the current image into a string. The strings
should be exactly the representation in either compressed or uncompressed form
