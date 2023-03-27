# fileupload

from fastapi import FastAPI,status, File, UploadFile, Request
import uvicorn
import logging


app = FastAPI()
LOGGER = logging.getLogger("Main Application")


@app.get("/upload",status_code=status.HTTP_200_OK)
def upload():
	LOGGER.info("file upload")
	if request.method == 'POST':
		f = request.files['file']
		f.save(f.filename)
		return render_template("Acknowledgement.html", name = f.filename)

if __name__ == '__main__':
	uvicorn.run('app:aap',host="0.0.0.0",port=8514,reload=True)
  

![image](https://user-images.githubusercontent.com/21003713/227717226-5872c45d-9de4-44f2-be2b-33a2cc8e906e.png)
