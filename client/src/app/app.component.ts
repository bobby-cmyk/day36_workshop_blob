import { HttpClient } from '@angular/common/http';
import { Component, ElementRef, inject, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { firstValueFrom } from 'rxjs';
import { Post } from '../models';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  standalone: false,
  styleUrl: './app.component.css'
})
export class AppComponent{
  @ViewChild('file') pictureFile!: ElementRef;
  
  private fb = inject(FormBuilder)
  private http = inject(HttpClient)

  uploadForm!: FormGroup;
  posts!: Post[]

  ngOnInit(): void {
    this.uploadForm = this.fb.group({
      'comments': this.fb.control('')
    })
  }

  upload() {
    const formData = new FormData();

    formData.set("comments", this.uploadForm.get("comments")?.value)
    formData.set('picture', this.pictureFile.nativeElement.files[0])

    firstValueFrom(
      this.http.post('/api/post', formData)
    ).then(response => console.log(response))
  }

  displayPictures() {

  }

  getAllPosts() {
    firstValueFrom(
      this.http.get<Post[]>('/api/post')
    ).then(response => this.posts = response)
  }
}
