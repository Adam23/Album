<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>相册首页</title>

  <link rel="stylesheet" href="css/main.css">
  <style>
    #tiles li {
      -webkit-transition: all 0.3s ease-out;
         -moz-transition: all 0.3s ease-out;
           -o-transition: all 0.3s ease-out;
              transition: all 0.3s ease-out;
    }
  </style>
  </head>
  
  <body>
  <div id="container">
    <header>
      <h1>电影长廊———— powered by Wookmark</h1>
    </header>
    <br/>

    <div id="main" role="main">

      <ul id="tiles">
      <%-- 
        <li>
          <img src="http://www.tdog8.com/demo/jquery/web_201304021722/images/pic/01.jpg" height="283" width="200" alt="相册ID1">
          <p>(1)</p>
        </li>
        <li class="stamped-last">
          <img src="http://www.tdog8.com/demo/jquery/web_201304021722/images/pic/02.jpg" height="300" width="200">
          <p>(2) Stamped to be the last!</p>
        </li>
        <li>
          <img src="http://www.tdog8.com/demo/jquery/web_201304021722/images/pic/03.jpg" height="252" width="200">
          <p>(3)</p>
        </li>
        <li class="stamped-first">
          <img src="http://www.tdog8.com/demo/jquery/web_201304021722/images/pic/04.jpg" height="158" width="200">
          <p>(4) Stamped to be the first!</p>
        </li>
        <li>
          <img src="http://www.tdog8.com/demo/jquery/web_201304021722/images/pic/18.jpg" height="300" width="200">
          <p>(5)</p>
        </li>
        <li>
          <img src="http://www.tdog8.com/demo/jquery/web_201304021722/images/pic/09.jpg" height="297" width="200">
          <p>(6)</p>
        </li>
        <li>
          <img src="http://www.tdog8.com/demo/jquery/web_201304021722/images/pic/04.jpg" height="200" width="200">
          <p>(7)</p>
        </li>
        <li class="stamped-fourth">
          <img src="http://www.tdog8.com/demo/jquery/web_201304021722/images/pic/06.jpg" height="200" width="200">
          <p>(8) Stamped to be the fourth!</p>
        </li>
        <li>
          <img src="http://www.tdog8.com/demo/jquery/web_201304021722/images/pic/05.jpg" height="398" width="200">
          <p>(9)</p>
        </li>
        <li>
          <img src="http://www.tdog8.com/demo/jquery/web_201304021722/images/pic/07.jpg" height="267" width="200">
          <p>(10)</p>
        </li> --%>
  		<c:forEach items="${album}" var="item">
        	<li>
	        	<a href="${pageContext.request.contextPath}/photo/gallery.action?albumid=${item[0].albumId}">
	        	<img src="${pageContext.request.contextPath}/upload/${item[1].fileName}" width="300"/></a>
	        	<p>${item[0].albumName}</p>
        	</li>
        </c:forEach>

      </ul>
    </div>
  </div>
  
  	<script src="js/jquery-1.11.1.js"></script>
   	<script src="js/jquery.imagesloaded.js"></script>
  	<script src="js/jquery.wookmark.js"></script>
<!-- Once the page is loaded, initalize the plug-in. -->
  <script type="text/javascript">
    (function ($){
      $('#tiles').imagesLoaded(function() {
        function comparatorIsStamped(a, b) {
          var $a = $(a), $b = $(b);
          // Check if tile should be the first one
          if (!$a.hasClass('stamped-first') && $b.hasClass('stamped-first'))
            return 1;
          // Check if tile should be the last one
          if ($a.hasClass('stamped-last') && !$b.hasClass('stamped-last'))
            return 1;
          // Check if tile should the fourth one
          if (!$a.hasClass('stamped-fourth') && $b.hasClass('stamped-fourth') && $a.index() >= 4)
            return 1;
          return -1;
        }

        // Prepare layout options.
        var options = {
          autoResize: true, // This will auto-update the layout when the browser window is resized.
          container: $('#main'), // Optional, used for some extra CSS styling
          offset: 2, // Optional, the distance between grid items
          itemWidth: 210, // Optional, the width of a grid item
          comparator: comparatorIsStamped, // Used to sort the items
          direction: 'left' // Set this to 'right' if you want to stamp the 'stamped-first' item to the right
        };

        // Get a reference to your grid items.
        var $handler = $('#tiles li');

        $handler.each(function(i) {
          console.log($(this).index() + ':' + $(this).find('p').text());
        });

        // Call the layout function.
        $handler.wookmark(options);
      });
    })(jQuery);
    
    $(function(){
    	$("#tiles li").bind("click",function(){
    		var id=$(this).attr("id");
    		window.location.href="getPhotolist.action?gyid="+id;
    	});
    });
  </script>
  </body>
</html>
