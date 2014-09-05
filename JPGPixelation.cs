//see https://github.com/Zarkopafilis/Random-Notes/blob/master/JPGPixelation.java
final int PIX_SIZE = 10;
string fileName = "image";

Image img = Image.FromFile(fileName + ".jpg", true);

Bitmap src = new Bitmap(img);
Bitmap dest = src.clone();

for(int y = 0; y < src.getHeight(); y += PIX_SIZE) {
    for(int x = 0; x < src.getWidth(); x += PIX_SIZE) {

        double[] pixel = new double[3];
        pixel = src.getPixel(x, y, pixel);

        for(int yd = y; (yd < y + PIX_SIZE) && (yd < dest.getHeight()); yd++) {
            for(int xd = x; (xd < x + PIX_SIZE) && (xd < dest.getWidth()); xd++) {
                dest.setPixel(xd, yd, pixel);
            }
        }
    }
}

dest.Save(fileName + "-pixelated.jpg", System.Drawing.Imaging.ImageFormat.JPG);
