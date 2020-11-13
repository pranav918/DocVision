from flask import send_file, Flask, request, jsonify, url_for
import flask
import werkzeug
import cv2
from Helper import adaptivethresholding, ocr

app = Flask(__name__)


@app.route('/file-down/')
def file_downloads():
    print(request.base_url)
    return jsonify("ok")



@app.route('/posts_image',  methods = ['POST'])
def method():
    files_ids = list(flask.request.files)
    file_id = files_ids[0]   
    imagefile = flask.request.files[file_id]
    filename = werkzeug.utils.secure_filename(imagefile.filename)
    imagefile.save(filename)
    return jsonify("OK")


@app.route('/doOP/')
def doop():
    filename = request.args.get("image")+'.jpg'
    operation = request.args.get("operation")
    
    if operation == "adaptivethresholding":
        adaptivethresholding(filename)
        return jsonify(request.host_url+'getOP/')
    elif operation=="ocr":
        return jsonify(ocr(filename))
        
@app.route('/getOP/')
def getop():
    return send_file("op.jpg", as_attachment = True)
    
    
if __name__ == '__main__':
    app.run(host = "192.168.43.165", port=5000,threaded = False) 