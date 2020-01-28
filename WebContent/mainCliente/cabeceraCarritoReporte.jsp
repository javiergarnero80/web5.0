
<header id="header">
	<!--header-->
	<div class="header_top">
		<!--header_top-->
		<div class="container">
			<div class="row">
				<div class="col-sm-6 ">
					<div class="contactinfo">
						<ul class="nav nav-pills">
							<li><a href="#"><i class="fa fa-phone"></i>
									+549-0351-3255996</a></li>
							<li><a href="#"><i class="fa fa-envelope"></i>
									hotelmyj@gmail.com</a></li>
						</ul>
					</div>
				</div>
				<div class="col-sm-6">
					<div class="social-icons pull-right">
						<ul class="nav navbar-nav">
							<li><a href="#"><i class="fa fa-facebook"></i></a></li>
							<li><a href="#"><i class="fa fa-twitter"></i></a></li>
							<li><a href="#"><i class="fa fa-linkedin"></i></a></li>
							<li><a href="#"><i class="fa fa-dribbble"></i></a></li>
							<li><a href="#"><i class="fa fa-google-plus"></i></a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--/header_top-->

	<div class="header-middle">
		<!--header-middle-->
		<div class="container">
			<div class="row">
				<div class="col-sm-3">
					<div class="logo pull-left">
						<a href="${pageContext.request.contextPath}/eliminarCarrito.do"><img
							src="${pageContext.request.contextPath}/mainCliente/images/shop/logo.png"
							alt="" width="100px" height="98" /></a>
					</div>
				</div>
				<div class="col-sm-9">
					<div class="shop-menu pull-right">
						<ul class="nav navbar-nav">
							<!--<li><a href=""><i class="fa fa-user"></i>Cuenta</a></li>-->

							<% if(session.getAttribute("email") ==null && session.getAttribute("nivel")==null){%>
							<li><a href="login.jsp"><i class="fa fa-sign-in"></i>Iniciar
									Sesión</a></li>
							<li><a
								href="${pageContext.request.contextPath}/carrito.do?op=listarHabCarrito"><i
									class="fa fa-crosshairs"></i> Habitaciones y Servicios</a></li>
							<%}else{%>
							<li><a
								href="${pageContext.request.contextPath}/eliminarCarrito.do"><i
									class="fa fa-crosshairs"></i> Volver a Habitaciones y Servicios</a></li>
							<li>
								<p>|
								<p>
							</li>
							<li id="usuarioSesion">
								<p>
									Bienvenid@:
									<%=session.getAttribute("email") %></p>
							</li>
							<li><a
								href="${pageContext.request.contextPath}/cerrarSesion.do"><i
									class="fa fa-sign-out"></i>Cerrar Sesion</a></li>
							<%}%>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--/header-middle-->

	<div class="header-bottom">
		<!--header-bottom-->
		<div class="container">
			<div class="row">
				<div class="col-sm-9">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse"
							data-target=".navbar-collapse">
							<span class="sr-only">Toggle navigation</span> <span
								class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
					</div>
					<div class="mainmenu pull-left">
						<ul class="nav navbar-nav collapse navbar-collapse">
							<li><a
								href="${pageContext.request.contextPath}/carrito.do?op=listarHabCarrito">Inicio</a></li>
							<li class="dropdown"><a href="#" class="active">Hotel<i
									class="fa fa-angle-down"></i></a>
								<ul role="menu" class="sub-menu">
									<li><a
										href="${pageContext.request.contextPath}/eliminarCarrito.do"
										class="active">Habitaciones y Servicios</a></li>
								</ul></li>
							<!-- <li><a href="contact-us.html">Contacto<i
									class="fa fa-angle-down"></i></a>
							<ul role="menu" class="sub-menu">
									<li><a href="shop.html" class="active">Contacto</a></li>
									<li><a href="product-details.html">Sugerencias</a></li>
							</ul></li>-->
						</ul>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="search_box pull-right">
						<input type="text" placeholder="Search" />
					</div>
				</div>
			</div>
		</div>
	</div>
</header>
