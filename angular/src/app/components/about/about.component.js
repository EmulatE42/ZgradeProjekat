"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var core_1 = require("@angular/core");
var AboutComponent = (function () {
    function AboutComponent() {
        this.users = [{ email: "djuro.95@live.com", firstName: "Stefan", lastName: "Djuric", image: "https://scontent.fbeg5-1.fna.fbcdn.net/v/t1.0-9/14517637_10208885138099175_7812064864356277628_n.jpg?oh=072f05e63acf6965f6d5069be046463e&oe=5AAEEE4E", html_url: "https://www.facebook.com/stefan.djuric" },
            { email: "momir@live.com", firstName: "Momir", lastName: "Kostic", image: "https://scontent.fbeg5-1.fna.fbcdn.net/v/t1.0-9/998848_623457747734032_564093331_n.jpg?oh=36cc11eacbd75a2db32c7865f2afdb7c&oe=5A67ABC2", html_url: "https://www.facebook.com/momir.kostic.5" },
            { email: "kospic@live.com", firstName: "Miroslav", lastName: "Kospic", image: "https://scontent.fbeg5-1.fna.fbcdn.net/v/t1.0-9/1912094_10204346431220197_8314740096784442896_n.jpg?oh=1790bd440ba069008a3b85c34fa31788&oe=5A61FA34", html_url: "https://www.facebook.com/miroslavdelija" },
            { email: "ivan@live.com", firstName: "Ivan", lastName: "Mihajlov", image: "https://scontent.fbeg5-1.fna.fbcdn.net/v/t1.0-9/18881790_10208451606998564_7355731358915699876_n.jpg?oh=213d6a12faf9853ee627454b7ccc7951&oe=5AA3BD9A", html_url: "https://www.facebook.com/traxxax" }];
    }
    return AboutComponent;
}());
AboutComponent = __decorate([
    core_1.Component({
        moduleId: module.id,
        selector: 'about',
        templateUrl: './about.component.html'
    })
], AboutComponent);
exports.AboutComponent = AboutComponent;
//# sourceMappingURL=about.component.js.map