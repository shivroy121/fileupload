import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FileDetails } from '../file-details.model';
import { FileUploadService } from '../services/file-upload.service';
import { AgChartOptions } from 'ag-charts-community';

@Component({
  selector: 'app-upload-file',
  templateUrl: './upload-file.component.html',
  styleUrls: ['./upload-file.component.css']
})
export class UploadFileComponent implements OnInit {

  file!: File;
  fileDetails: FileDetails[]=[];
  

  public options!: AgChartOptions;

  xval: any[] = [];


  constructor(private fileUploadService: FileUploadService, private router: Router) { 
    this.setOption(this.xval);
  }

  ngOnInit(): void {
  }

  selectFile(event: any) {
    this.file = event.target.files.item(0);
  }

  uploadFile() {
    this.fileUploadService.upload(this.file).subscribe({
      next: (data) => {
        this.fileDetails=data;

         let x:any[]=[];
        let y: any[] = [];
       
        for (let i = 0; i < data.length; i++) {

          let jsonX = { x: this.fileDetails[i].x, y: this.fileDetails[i].y };
         
         
          x.push(jsonX);
          
         
        }

        this.setOption(x);
       
        console.log("File Uploaded Successfully")
      },
      error: (e) => {
        console.log(e);
      }
    });
  }

  setOption(data:any) {
    
    this.options = {
      autoSize: true,
      title: {
        text: 'X vs Y',
      },
      series: [
        {
          type: 'scatter',
          title: 'X',
          data: data,
          xKey: 'x',
          xName: 'X',
          yKey: 'y',
          yName: 'Y',
          marker: {
            shape: 'square',
            size: 6,
            maxSize: 30,
            fill: 'rgba(227,111,106,0.71)',
            stroke: '#9f4e4a',
          },
        }
      ],
      axes: [
        {
          type: 'number',
          position: 'bottom',
          title: {
            text: 'X',
          },
          label: {
            rotation: 45,
            formatter: (params) => {
              return params.value ;
            },
          },
        },
        {
          type: 'number',
          position: 'left',
          title: {
            text: 'Y',
          },
          label: {
            formatter: (params) => {
              return params.value;
            },
          },
        },
      ],
    };
  }

}
