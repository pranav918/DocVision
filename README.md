# DocVision
# MOTIVATION
Since the Government of India has banned some of the Chinese apps like Camscanner there is a gap in industry. From this project we are trying to fill this gap. The motivation behind this app is to produce an application with the functionality of scanning and modifying PDFs.

# H/W AND S/W REQUIREMENTS
{1}Hardware required:\
Computer with intel i5 and above processor
Mobile phone with working camera
APIs and Libraries:
Design dependencies: ‘com.google.android.material:material:1.2.1’
Pytesseract API, Flask API
Deep Learning Libraries: Tensorflow, Keras, OpenCV
Helper Libraries: Numpy, matplotlib

# Technology Stack
Frontend: Java, XML, Android Studio
Backend: Flask
ML Libraries: Tensorflow, Keras, pytesseract

# USE CASE DIAGRAM
<p align="center">
<img src="https://github.com/lnx2000/DocVision/blob/main/Images/USE%20CASE%20DIAGRAM.png" height="400">
</p>

# ALGORITHMS/API
1.   Adaptive Thresholding
2.   Optical Character Recognition (OCR)
3.   Flask Server

# HIGH LEVEL DESIGN
<p align="center">
<img src="https://github.com/lnx2000/DocVision/blob/main/Images/HIGH%20LEVEL%20DESIGN.png" height="400">
</p>

# APPLICATION SCREENSHOTS
<img src="https://github.com/lnx2000/DocVision/blob/main/Images/ScreenShot1.png" height="400">
<img src="https://github.com/lnx2000/DocVision/blob/main/Images/ScreenShot2.png" height="400">
<img src="https://github.com/lnx2000/DocVision/blob/main/Images/ScreenShot3.png" height="400">

# RESULTS
<img src="https://github.com/lnx2000/DocVision/blob/main/Images/Result.png" height="400">

# API CALLS
<img src="https://github.com/lnx2000/DocVision/blob/main/Images/API%20Calls.png" height="400">

# CONCLUSION
We have successfully implemented DocVision app which will help to generate PDF from images. We have added some basic image processing like thresholding, color space conversions, etc. We have also added OCR (Optical Character Recognition) which will detect text from images. We have used Tesseract OCR for the same. We have also built a FLASK server to do heavy work like recognizing text, dealing with larger image sizes.

# FUTURE SCOPE
We will try to improve the app by more use of  Machine Learning. We can add functionalities like recognizing languages, language conversion, recognizing tables and converting into csv, editing PDFs, we will try to improve accuracy of text recognition models, auto detecting documents and aligning perspective. We will try to make the server efficient to handle multiple requests at a time, we will improve privacy by adding encryption of every image going to the servers.

