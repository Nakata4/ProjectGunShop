<!DOCTYPE html>
<html lang="en">
<head>
    <title>Films by the minute</title>
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/css/font-awesome.min.css" rel="stylesheet">
    <link href="assets/css/main.css" rel="stylesheet">
    <script src="js/vendor/jquery/jquery-1.12.1.min.js"></script>
    <script src="js/vendor/bootstrap/bootstrap.min.js"></script>
    <script src="js/main.js"></script>
</head>
<body class="">
<div class="login-page">
    <header>
        <div class="container">
            <nav class="navbar-default">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12">
                            <img src="assets/img/logo.png">
                            <h1>Film App</h1>
                        </div>
                    </div>
                </div>
            </nav>
        </div>
    </header>
    <div class="container">
        <div class="row header-info">
            <div class="col-md-12">
                <div class="basic-panel header-info">
                    <h2>Social and online films�</h2>
                    <p>Search for your favouret film</p>
                </div>
            </div>
        </div>
        <div class="row login-container">
            <div class="col-md-7">
                <div class="basic-panel hidden-sm hidden-xs">
                    <img class="img-responsive " src="assets/img/film.jpg">
                </div>
            </div>
            <div class="col-md-5">          
                <form class="basic-panel" action="FIlmAPI" method="POST">                               	
                    <div class="form-group">
                        <label for="username">User</label>
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-user"></i></span>
                            <input type="text" class="form-control" id="username" name="username" placeholder="Потребител">
                        </div>
                        <p class="help-block">User not found</p>
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                            <input type="password" class="form-control" id="password" name="password" placeholder="Парола">
                        </div>
                        <p class="help-block">password required</p>
                    </div>
                    <input type="hidden" name="action" value="login">
                    <button id="login" type="submit" class="btn btn-primary">Login</button>
                    <button id="register" type="button" class="btn btn-success pull-right">Registration</button>
                </form>
            </div>
        </div>
    </div>
    <div id="register-modal" class="modal fade" tabindex="-1" role="dialog" style="display: none;">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                    <h4 class="modal-title">New User</h4>
                </div>
                <div class="modal-body">
                    <form id="register-form" action="FilmAPI" method="POST">
                        <div class="form-group">
                            <label for="register-user">User</label>
                            <input type="text" class="form-control" id="register-user" name="username" placeholder="Потребител">
                            <p class="help-block">User not found</p>
                        </div>
                        <div class="form-group">
                            <label for="register-email">Email</label>
                            <input type="email" class="form-control" id="register-email" name="email" placeholder="example@gmail.com">
                            <p class="help-block"> "Email" required</p>
                        </div>
                        <div class="form-group">
                            <label for="register-pass">password</label>
                            <input type="password" class="form-control" id="register-pass" name="password" placeholder="Парола">
                            <p class="help-block">Wrong password </p>
                        </div>
                        <div class="form-group">
                            <label for="confirm-register-pass">Repeat password</label>
                            <input type="password" class="form-control" id="confirm-register-pass" name="repeatPassword" placeholder="Парола">
                            <p class="help-block">Password</p>
                        </div>                       
                        <input type="hidden" name="action" value="register">
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="submit" form="register-form" class="btn btn-success">Login</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
</div>
<script>
    bindEvents();
</script>
</body>
</html>