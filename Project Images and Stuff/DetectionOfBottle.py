import cv2;
import numpy as np;
import matplotlib.pyplot as plt

image = cv2.imread('C:\Users\Deepak\Desktop\DIP Project Images\\roger.png', cv2.IMREAD_COLOR)
original = np.copy(image)
if image is None:
    print 'Can not read/find the image.'
    exit(-1)

hsv_image = cv2.cvtColor(image, cv2.COLOR_BGR2HSV)
H,S,V = hsv_image[:,:,0], hsv_image[:,:,1], hsv_image[:,:,2]
V = V * 2

hsv_image = cv2.merge([H,S,V])
image = cv2.cvtColor(hsv_image, cv2.COLOR_HSV2RGB)
image = cv2.cvtColor(image, cv2.COLOR_RGB2GRAY)
# plt.figure(), plt.imshow(image)

Dx = cv2.Sobel(image,cv2.CV_8UC1,1,0)
Dy = cv2.Sobel(image,cv2.CV_8UC1,0,1)
M = cv2.addWeighted(Dx, 1, Dy,1,0)

# plt.subplot(1,3,1), plt.imshow(Dx, 'gray'), plt.title('Dx')
# plt.subplot(1,3,2), plt.imshow(Dy, 'gray'), plt.title('Dy')
# plt.subplot(1,3,3), plt.imshow(M, 'gray'), plt.title('Magnitude')

ret, binary = cv2.threshold(M,10,255, cv2.THRESH_BINARY | cv2.THRESH_OTSU)
# plt.figure(), plt.imshow(binary, 'gray')

binary = binary.astype(np.uint8)
binary = cv2.morphologyEx(binary, cv2.MORPH_CLOSE, cv2.getStructuringElement(cv2.MORPH_ELLIPSE, (20, 20)))
edges = cv2.Canny(binary, 50, 100)
# plt.figure(), plt.imshow(edges, 'gray')

lines = cv2.HoughLinesP(edges,1,3.14/180,50,20,10)[0]
output = np.zeros_like(M, dtype=np.uint8)
for line in lines:
    cv2.line(output,(line[0],line[1]), (line[2], line[3]), (100,200,50), thickness=2)
# plt.figure(), plt.imshow(output, 'gray')

points = np.array([np.transpose(np.where(output != 0))], dtype=np.float32)
rect = cv2.boundingRect(points)
cv2.rectangle(original,(rect[1],rect[0]), (rect[1]+rect[3], rect[0]+rect[2]),(255,255,255),thickness=2)
original = cv2.cvtColor(original,cv2.COLOR_BGR2RGB)
plt.figure(), plt.imshow(original,'gray')

print(points)

plt.show()
