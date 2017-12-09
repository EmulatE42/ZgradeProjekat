import { Component } from '@angular/core';

import { BugDTO, CommentDTO, UserDTO } from "../../models";
import { TenantService } from "../../services/tenant.service";
import { Router, ActivatedRoute }    from '@angular/router';
import {LoggedUtils} from "../../utils/logged.utils";

@Component({
  moduleId: module.id,
  selector: 'bug',
  templateUrl: './bug.component.html',
  styleUrls: ['./bug.component.css'],
  providers: [TenantService, LoggedUtils]

})
export class BugComponent
{
  newComment:string;
  bug:BugDTO;
  currentUser:string;
  currentUserId:number;
  comment:CommentDTO;

  constructor(private tenantService: TenantService, private route: ActivatedRoute, private _router: Router)
  {
    this.tenantService.getBug(this.route.snapshot.params['p1']).subscribe(
      (data:BugDTO) => this.bug = data
    );
    this.currentUser = this.tenantService.getUsername();
    this.currentUserId = this.tenantService.getId();
  }

  addComment()
  {
    this.comment = new CommentDTO(null,this.newComment, new UserDTO(this.tenantService.getId(), this.currentUser));
    this.bug.comments.push(this.comment);
    this.tenantService.addComment(this.route.snapshot.params['p1'], this.comment).subscribe(
      () => this.removeComment()
    );
  }

  removeComment()
  {
    this.newComment = "";
  }

  deleteComment(commentId:number, index:number)
  {
    this.bug.comments.splice(index,1);
    this.tenantService.deleteComment(commentId, this.route.snapshot.params['p1']).subscribe(
      data => console.log(data)
    );
  }
}
