// footer
function writeFooter(path, area) {
	theFooter = 
	'<div align="center">' +
		'<div id="footer" align="center">&copy;2013&nbsp;XEROX CORPORATION. All rights reserved.<br />' +
		'<a href="javascript:document.location.href=\'' + path + '\help/privacy.html\'" target="_blank">Privacy Policy</a>&nbsp;&#124;&nbsp;';
	switch (area)
      {
         case "General":
			theFooter +=
			'<a href="javascript:document.location.href=\'' + path + '\help/sitemap_public.html\'" target="_blank">Site Map</a>&nbsp;&#124;&nbsp;';
			break;
			
			case "Provider":
			theFooter +=
			'<a href="javascript:document.location.href=\'' + path + '\help/sitemap_provider.html\'" target="_blank">Site Map</a>&nbsp;&#124;&nbsp;';
			break;
			
			case "Member":
			theFooter +=
			'<a href="javascript:document.location.href=\'' + path + '\help/sitemap_member.html\'" target="_blank">Site Map</a>&nbsp;&#124;&nbsp;';
			break;
			
			case "Internal":
			theFooter +=
			'<a href="javascript:document.location.href=\'' + path + '\help/sitemapinternal.html\'" target="_blank">Site Map</a>&nbsp;&#124;&nbsp;';
			break;
			default:
			theFooter +=
			'<a href="javascript:document.location.href=\'' + path + '\help/sitemap_public.html\'" target="_blank">Site Map</a>&nbsp;&#124;&nbsp;';
			break;
		}

	
	theFooter +=	
		'<a href="javascript:document.location.href=\'' + path + '\help/tou.html\'" target="_blank">Terms of Use</a>&nbsp;&#124;&nbsp;' +
		'<a href="javascript:document.location.href=\'' + path + '\help/browser.html\'" target="_blank">Browser Requirements</a>&nbsp;&#124;&nbsp;' +
		'<a href="javascript:document.location.href=\'' + path + '\help/accessibility.html\'" target="_blank">Accessibility Compliance</a></div>' +
		'<div>&nbsp;</div>' +
	'</div>';
	document.write(theFooter);
}