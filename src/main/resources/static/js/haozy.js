$(function() {

	$(window).scroll(function() {

		var $top = $(window).scrollTop();

		if ($top > 0) {

			$('.header').addClass('fixed');

			$('.site-header').addClass('fixed');

		} else {

			$('.header').removeClass('fixed');

			$('.site-header').removeClass('fixed');

		}

		var $sideH = $('.sidebar').height() + $('.sidebar').offset().top;

		var $scrollT = $top + $('#scroll').height();

		var $footT = $('.footer').offset().top;

		if ($top > $sideH) {

			if ($scrollT > $footT) {

				$('#scroll').addClass('stop').removeClass('scroll');

			} else {

				$('#scroll').addClass('scroll').removeClass('stop');

			}

		} else {

			$('#scroll').removeClass('scroll').removeClass('stop');

		}

	});

});