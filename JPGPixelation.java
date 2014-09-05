//Pixelation magnitude
final int PIX_SIZE = 10;
String fileName = "image"
// Read the JPG
img = ImageIO.read(new File(fileName + ".jpg"));

// Get the raster data (array of pixels)
Raster src = img.getData();

// Create an output raster
WritableRaster dest = src.createCompatibleWritableRaster();

// Loop through pixels based on magnitude (more magnitude = more pixels skipped)
for(int y = 0; y < src.getHeight(); y += PIX_SIZE) {
    for(int x = 0; x < src.getWidth(); x += PIX_SIZE) {

        //Copy the pixels data
        double[] pixel = new double[3];
        pixel = src.getPixel(x, y, pixel);

        // "Paste" the pixel onto the surrounding PIX_SIZE by PIX_SIZE neighbors
        // Also make sure that our loop never goes outside the bounds of the image
        
        //Study that
        for(int yd = y; (yd < y + PIX_SIZE) && (yd < dest.getHeight()); yd++) {
            for(int xd = x; (xd < x + PIX_SIZE) && (xd < dest.getWidth()); xd++) {
                dest.setPixel(xd, yd, pixel);
            }
        }
    }
}

// Save the raster back to the Image
img.setData(dest);

//Write the new JPG
ImageIO.write(img, "jpg", new File(fileName+ "-pixelated.jpg"));
