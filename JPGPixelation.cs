//see https://github.com/Zarkopafilis/Random-Notes/blob/master/JPGPixelation.java
final int PIX_SIZE = 10;
string fileName = "image";

//get JPG - img

//get Raster data - src
//get Writable raster - dest

for(int y = 0; y < src.getHeight(); y += PIX_SIZE) {
    for(int x = 0; x < src.getWidth(); x += PIX_SIZE) {

        //Copy the pixels data
        double[] pixel = new double[3];
        pixel = src.getPixel(x, y, pixel);

        // Put the pixels data to an "big" square ( PIX_SIZE * PIX_SIZE ) made of pixels
        // Also make sure that our loop never goes outside the bounds of the image
        for(int yd = y; (yd < y + PIX_SIZE) && (yd < dest.getHeight()); yd++) {
            for(int xd = x; (xd < x + PIX_SIZE) && (xd < dest.getWidth()); xd++) {
                dest.setPixel(xd, yd, pixel);
            }
        }
    }
}

//set img data - dest

//save
