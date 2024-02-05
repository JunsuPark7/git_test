jQuery(function ($) {
	// Open
	$('[data-toggle="sidebar"]').on('click', function() {
		$('body').toggleClass('sidebar-open');
	});

	// Close
	$('[data-close="sidebar"]').on('click', function() {
		$('body').removeClass('sidebar-open');
	});

	// Toggle immersive mode
	$('[data-check-immersive]').change('click', function() {
		$('.app-sidebar').toggleClass('immersive');
	});

	$('[data-check-sticky]').change('click', function() {
		$('.sidebar-header').toggleClass('sticky');
	});

	$('[data-toggle="more"]').on('click', function(e) {
		$(this).closest('.expandable').toggleClass('expandable--expanded');
	});
});