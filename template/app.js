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
	            
	        } else if ( window.innerWidth < 900 ) {
	            main.css('padding-top',height-50);
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

function ArticleDetail__Body__init() {
	if ( toastui === undefined ){
		return;
	}
	
	const options = {
		theme: {
			viewer: {
				fontSize : 30,
				fontFamily : 'crayon',
				color : 'white',
			},
		},
	};
	
	let content = document.querySelector('.detail__article-content').innerHTML;
	var viewer = new toastui.Editor.factory({
		el : document.querySelector('.detail__article-content'),
		initialValue : content,
		viewer : true,
		
	});
	
}

Article__head__init();
Article_menuInit();
ArticleDetail__Body__init();

