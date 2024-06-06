import { Component } from '@angular/core';
import { MediaServiceService } from './Services/MediaService/media-service.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'GymBuddy';

  url?: string;

  constructor(private mediaService: MediaServiceService) {}

  upload(event: any) {
    const file = event.target.files[0];
    if (file) {
      const formData = new FormData();
      formData.append('file', file);
      this.mediaService.uploadFile(formData)
        .subscribe((res) => {
          console.log("response ", res);
          this.url = res.url;
        });
    }
  }
}
