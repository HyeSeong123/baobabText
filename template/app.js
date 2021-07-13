function Article_menuInit(){
	    var height = $('.main__banner').innerHeight();
	    var main = $('main');
	    var top_hamburger = $('.top-bar__hamburger');
	    if(window.innerWidth > 900){
	        main.css('padding-top',height-70);
	    } else if ( window.innerWidth < 900 ) {
	        main.css('padding-top',height-50);
	    }
	    $(window).resize(function(){
	        height = $('.main__banner').innerHeight();
	         if(window.innerWidth > 900){
	            main.css('padding-top',height-70);
	            
	        } else if ( window.innerWidth < 900 && window.innerWidth >= 363) {
	            main.css('padding-top',height-80);
	        }
			else if ( window.innerWidth < 363) {
	            main.css('padding-top',height-60);
	        }
	         top_hamburger.removeClass('open');
	         $('.top-menu__menu').removeClass('open');
	    })
	    $(document).ready(function(){
	    	top_hamburger.click(function(){
			$(this).toggleClass('open');
	        
	        if(window.innerWidth > 900){
	            $('.top-menu__pc-menu').toggleClass('open');
	        }
	        else if(window.innerWidth > 250 && window.innerWidth < 900){
	            $('.top-menu__mobile-menu').toggleClass('open');
	            $('body').toggleClass('scroll-lock');
	        }
		});
	});
	var subOpenToggle = 0;
	 $('.top-menu__mobile-menu > ul > li > a').click(function(){
	     var height;
	     var thisis = $(this);
	    if(thisis.text().indexOf('Web Programs') != -1){
	        height = 240;
	    } else if(thisis.text().indexOf('DataBase') != -1){
	        height = 60;
	    } else if(thisis.text().indexOf('Server') != -1){
	        height = 60;
	    } else if(thisis.text().indexOf('Free') != -1){
	        height = 60;
	    }
	    thisis.closest('ul>li').toggleClass('subOpen');
	    if(thisis.parent().hasClass('subOpen')){
	    	thisis.parent().children('ul').css('height', height);
	        subOpenToggle = 1;
	    } else if(thisis.parent().hasClass('subOpen') == false){
	        height = 0;
	        thisis.parent().children('ul').css('height', height);
	        subOpenToggle = 0;
	    }
	 });
}

function Article__head__init(){
	var isMobile = false;
    var filter = "win16|win32|win64|mac";
    var width = window.innerWidth;
    var logoP = $('.logo__P');
    var logoB = $('.logo__B');
    if (navigator.platform) {
        isMobile = filter.indexOf(navigator.platform.toLowerCase()) < 0;
    }
    
    if(isMobile == false){
    	var logo = $('.logo');
    	
    	if(width > 1350){
        	logo.mouseenter(function(){
        		gsap.to(".logo__P", {duration:0.5, ease:"power2.inOut" , x:30});
        		gsap.to(".logo__B", {duration:0.5, ease:"power2.inOut" , x:125});
        	});
        	logo.mouseleave(function(){
        		gsap.to(".logo__P", {duration:0.5, ease:"power2.inOut" , x: 0});
        		gsap.to(".logo__B", {duration:0.5, ease:"power2.inOut" , x: 0});
        	});
    	}
    	else if(width < 1350 && width > 900){
        	logo.mouseenter(function(){
        		gsap.to(".logo__P", {duration:0.5, ease:"power2.inOut" , x:20});
        		gsap.to(".logo__B", {duration:0.5, ease:"power2.inOut" , x:85});
        	});
        	logo.mouseleave(function(){
        		gsap.to(".logo__P", {duration:0.5, ease:"power2.inOut" , x: 0});
        		gsap.to(".logo__B", {duration:0.5, ease:"power2.inOut" , x: 0});
        	});
    	}
    	else if(width < 900){
        	logo.mouseenter(function(){
        		gsap.to(".logo__P", {duration:0.5, ease:"power2.inOut" , x:0});
        		gsap.to(".logo__B", {duration:0.5, ease:"power2.inOut" , x:0});
        	});
        	logo.mouseleave(function(){
        		gsap.to(".logo__P", {duration:0.5, ease:"power2.inOut" , x: 0});
        		gsap.to(".logo__B", {duration:0.5, ease:"power2.inOut" , x: 0});
        	});
    	}
    	$(window).resize(function(){
    		width = window.innerWidth;
        	if(width > 1350){
            	logo.mouseenter(function(){
            		gsap.to(".logo__P", {duration:0.5, ease:"power2.inOut" , x:30});
            		gsap.to(".logo__B", {duration:0.5, ease:"power2.inOut" , x:125});
            	});
            	logo.mouseleave(function(){
            		gsap.to(".logo__P", {duration:0.5, ease:"power2.inOut" , x: 0});
            		gsap.to(".logo__B", {duration:0.5, ease:"power2.inOut" , x: 0});
            	});
        	}
        	else if(width < 1350 && width > 900){
            	logo.mouseenter(function(){
            		gsap.to(".logo__P", {duration:0.5, ease:"power2.inOut" , x:20});
            		gsap.to(".logo__B", {duration:0.5, ease:"power2.inOut" , x:85});
            	});
            	logo.mouseleave(function(){
            		gsap.to(".logo__P", {duration:0.5, ease:"power2.inOut" , x: 0});
            		gsap.to(".logo__B", {duration:0.5, ease:"power2.inOut" , x: 0});
            	});
        	}
        	else if(width < 900){
            	logo.mouseenter(function(){
            		gsap.to(".logo__P", {duration:0.5, ease:"power2.inOut" , x:0});
            		gsap.to(".logo__B", {duration:0.5, ease:"power2.inOut" , x:0});
            	});
            	logo.mouseleave(function(){
            		gsap.to(".logo__P", {duration:0.5, ease:"power2.inOut" , x: 0});
            		gsap.to(".logo__B", {duration:0.5, ease:"power2.inOut" , x: 0});
            	});
        	}
    	});
    }
}
var indexP = 1;
ball = $('.ball');
function index_ballInit(index){
	let ballIndex = $('.ball' + index);
	ball.removeClass('ball-selected');
	if(indexP == 1){
		ballIndex.addClass('ball-selected');
	}
	if(indexP == 2){
		ballIndex.addClass('ball-selected');
	}
	if(indexP == 3){
		ballIndex.addClass('ball-selected');
	}
	if(indexP == 4){
		ballIndex.addClass('ball-selected');
	}
}

Article__head__init();
Article_menuInit();
index_ballInit(indexP);

var width = $('.list_slide').width() + $('.arrow').width();
var repeat = 0;

$('.left_arrow').click(function(){
	
	if(repeat < 0){
		repeat=  repeat + 1;
	}
	indexP = (repeat*-1) + (+1);
	index_ballInit(indexP);
	gsap.to('.list_slide',{
		x: width*(repeat),
		duration: 0.5
	});
});

$('.right_arrow').click(function(){
	
	if(repeat >= -2){
		repeat = repeat - 1;
	}
	indexP = (repeat*-1) + (+1);
	index_ballInit(indexP);
	gsap.to('.list_slide',{
		x: width*(repeat),
		duration: 0.5
	});
});
$('.ball').click(function(){
	var index = ball.index(this);
	
	indexP = index+1;
	repeat = index*-1;
	ball.removeClass('ball-selected');
	$('.ball' + indexP).addClass('ball-selected');
	
	gsap.to('.list_slide',{
		x: width*(repeat),
		duration: 0.7
	});
});
//유튜브 플러그인 시작
function youtubePlugin() {
  toastui.Editor.codeBlockManager.setReplacer('youtube', youtubeId => {
    // Indentify multiple code blocks
    const wrapperId = `yt${Math.random().toString(36).substr(2, 10)}`;

    // Avoid sanitizing iframe tag
    setTimeout(renderYoutube.bind(null, wrapperId, youtubeId), 0);

    return `<div id="${wrapperId}"></div>`;
  });
}

function renderYoutube(wrapperId, youtubeId) {
  const el = document.querySelector(`#${wrapperId}`);

  el.innerHTML = `<div class="toast-ui-youtube-plugin-wrap"><iframe src="https://www.youtube.com/embed/${youtubeId}" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe></div>`;
}
// 유튜브 플러그인 끝

// codepen 플러그인 시작
function codepenPlugin() {
  toastui.Editor.codeBlockManager.setReplacer('codepen', url => {
    const wrapperId = `yt${Math.random().toString(36).substr(2, 10)}`;

    // Avoid sanitizing iframe tag
    setTimeout(renderCodepen.bind(null, wrapperId, url), 0);

    return `<div id="${wrapperId}"></div>`;
  });
}

function renderCodepen(wrapperId, url) {
  const el = document.querySelector(`#${wrapperId}`);
  
  var urlParams = new URLSearchParams(url.split('?')[1]);
  var height = urlParams.get('height');

  el.innerHTML = `<div class="toast-ui-codepen-plugin-wrap"><iframe height="${height}" scrolling="no" src="${url}" frameborder="no" loading="lazy" allowtransparency="true" allowfullscreen="true"></iframe></div>`;
}
// codepen 플러그인 끝

function ArticleDetail__Body__init() {
	let content = document.querySelector('.detail__article-content').innerHTML;
	var Vcount = $('.viewer').length;
	var Ecount = $('.editor').length;
	console.log(Vcount);
	console.log(Ecount);
		var viewer = new toastui.Editor.factory({
			el : document.querySelector('.viewer'),
			initialValue : content,
			viewer : true,
			plugins : [youtubePlugin, codepenPlugin]
		});	
}

ArticleDetail__Body__init();