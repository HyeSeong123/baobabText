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
	if(indexP == 5){
		ballIndex.addClass('ball-selected');
	}
	if(indexP == 6){
		ballIndex.addClass('ball-selected');
	}
	if(indexP == 7){
		ballIndex.addClass('ball-selected');
	}
	if(indexP == 8){
		ballIndex.addClass('ball-selected');
	}
}

Article__head__init();
Article_menuInit();
index_ballInit(indexP);

var width = $('.list_slide').width();
var widthMinus = width/0.835 - width;
var widthMinus2 = width/0.867 - width;
var width2 = width + widthMinus2;
	
width = width + widthMinus;

var repeat = 0;
var win_width = $(window).width();
var ball_end = 0;
if(win_width >= 1000){
	ball_end = -2;
}
if(win_width >= 700 && win_width < 1000){
	ball_end = -3;
}
if(win_width >= 240 && win_width < 700){
	ball_end = -4;
}
$('.left_arrow').click(function(){
	if(repeat < 0){
		repeat=  repeat + 1;
	}
	indexP = (repeat*-1) + (+1);
	index_ballInit(indexP);
	if(repeat == 0){
		gsap.to('.list_slide',{
		x: width*(repeat),
		duration: 0.5
		});	
	}
	if(repeat < 0){
		gsap.to('.list_slide',{
		x: width2*(repeat),
		duration: 0.5
		});	
	}
});

$('.right_arrow').click(function(){
	if(repeat >= ball_end){
		repeat = repeat - 1;
	}
	indexP = (repeat*-1) + (+1);
	index_ballInit(indexP);
	if(repeat == 0){
		gsap.to('.list_slide',{
		x: width*(repeat),
		duration: 0.5
		});
	}
	if(repeat < 0){
		gsap.to('.list_slide',{
		x: width2*(repeat),
		duration: 0.5
		});
	}
	
});
$('.ball').click(function(){
	var index = ball.index(this);
	
	indexP = index+1;
	repeat = index*-1;
	ball.removeClass('ball-selected');
	$('.ball' + indexP).addClass('ball-selected');
	
	if(repeat == 0){
		gsap.to('.list_slide',{
			x: width*(repeat),
			duration: 0.7
		});
	}
	if(repeat < 0){
		gsap.to('.list_slide',{
			x: width2*(repeat),
			duration: 0.7
		});
	}
});
function sizeInit(){
	width = $('.list_slide').width();
	widthMinus = width/0.83 - width;
	widthMinus2 = width/0.864 - width;
	width2 = width + widthMinus2;
}
$(window).resize(function(){
	sizeInit();
	repeat=0;
	indexP = 1;
	index_ballInit(1);
	win_width = $(window).width();
	if(win_width >= 1000){
	ball_end = -2;
	}
	if(win_width >= 240 && win_width < 900){
		ball_end = -4;
	}
	gsap.to('.list_slide',{
		x: 0,
		duration: 0.7
	});
	
})
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
	
	function Editor__init() {
	  $('.editor').each(function(index, node) {
	    var initialValue = $(node).prev().html().trim().replace(/t-script/gi, 'script');
	    var editor = new toastui.Editor({
	      el: node,
	      previewStyle: 'preview',
	      initialValue: initialValue,
	      height:600,
	      plugins: [youtubePlugin, codepenPlugin]
	    });
	  });
	}
	Editor__init();
	
	function EditorViewer__init() {
		$('.viewer').each(function(index,node){
			var initialValue = $(node).prev().html().trim().replace(/t-script/gi, 'script');
			var viewer = new toastui.Editor.factory({
			el : node,
			initialValue : initialValue,
			viewer : true,
			plugins : [youtubePlugin, codepenPlugin]
			});	
		});
	}
	EditorViewer__init();
}
$('.btn_up_box').click(function(){
	btnUp_action();
});
function btnUp_action(){
	window.scrollTo(0,0);
}
var oldVal = $('.searchInput');
$(".searchInput").on("propertychange change keyup paste input", function() {
	setTimeout(function() {
		var currentVal = $('.searchInput').val();
		if(currentVal == oldVal) {
			return;
		}
		oldVal = currentVal;
		
		var list = $('.article-list').toArray();
		var listNum = 1;
		list.forEach(function(c, i){
			i= i + 1;
			var currentList = c;
			var currentListText = c.innerText;
			var originRomeNum = 'I';
			var changeRomeNum = 'I';
			if ( i == 1){
				originRomeNum = 'I';
			} else if ( i == 2){
				originRomeNum = 'II';
			}  else if ( i == 3){
				originRomeNum = 'III';
			}  else if ( i == 4){
				originRomeNum = 'IV';
			}  else if ( i == 5){
				originRomeNum = 'V';
			}  else if ( i == 6){
				originRomeNum = 'VI';
			}  else if ( i == 7){
				originRomeNum = 'VII';
			}  else if ( i == 8){
				originRomeNum = 'VIII';
			}  else if ( i == 9){
				originRomeNum = 'IX';
			}  else if ( i == 10){
				originRomeNum = 'X';
			}
			if ( listNum == 1){
				changeRomeNum = 'I';
			} else if ( listNum == 2){
				changeRomeNum = 'II';
			}  else if ( listNum == 3){
				changeRomeNum = 'III';
			}  else if ( listNum == 4){
				changeRomeNum = 'IV';
			}  else if ( listNum == 5){
				changeRomeNum = 'V';
			}  else if ( listNum == 6){
				changeRomeNum = 'VI';
			}  else if ( listNum == 7){
				changeRomeNum = 'VII';
			}  else if ( listNum == 8){
				changeRomeNum = 'VIII';
			}  else if ( listNum == 9){
				changeRomeNum = 'IX';
			}  else if ( listNum == 10){
				changeRomeNum = 'X';
			}
			if(currentListText.includes(currentVal) == false){
				currentList.style.display = "none";
			}
			if(currentListText.includes(currentVal)){
				currentList.style.display = "flex";
				c.innerHTML = c.innerHTML.replace('<span class="article-' + i + '">' + originRomeNum, '<span class="article-' + listNum + '">' + changeRomeNum);
				listNum ++;
			}
			if(currentVal == ''){
				currentList.style.display = "flex";
			}
		});
		
	},600);
});
ArticleDetail__Body__init();