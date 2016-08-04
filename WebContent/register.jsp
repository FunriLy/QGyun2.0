<%@ page pageEncoding="UTF-8" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
	<head>
	 <link rel="stylesheet" type="text/css" href="css/register.css" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>注册我的博客</title>
	</head>
	<body>
	
	
	
<div class="main">
		<div class="header" >
		 <!-- 	输出注册错误信息 --> 
	 
			<h1>会员注册</h1>
		</div>
		<h4>Welcome registration. </h4>
			<form action="Register" method='post'>
				<ul class="form">
		
					<li>
						<input type="text"  id="name" name='username' maxlength='12' placeholder="用户名，12字符内" required/>
					
						<div class="clear"> </div>
					</li> 
					<li>
						<input type="password" id="password" name='password' maxlength='16'  placeholder="密码，6至16字符" required/>
					
						<div class="clear"> </div>
					</li> 
					<li>
						<input type="password" name='confirmedPassword' id='confirmedPassword'  maxlength='16'  placeholder="确认密码" required/>
					
						<div class="clear"> </div>
					</li> 

				
						<li>
						<p> &nbsp 设置密保</p>
							<div class="dropdown">
				
							<select name="question" style="width:450px " >
					         <option value="hometown">  &nbsp 你的家乡是？ </option>
					         <option value="birthday"> &nbsp 你的生日？</option>
					         <option valus="fathername"> &nbsp 你父亲的名字？</option>
							<option value="mothername"> &nbsp 你母亲的生日？</option>
						     </select>
						     </div>
					</li>
					<li>
						<input type="text"  name='answer'  placeholder="密保答案" required/>
					
						<div class="clear"> </div>
					</li> 

					<input type="submit"  value="注册" onclick="checkuser()">

					<a style="text-decoration: none" href='Getall'><input type="button" value='返回'></a>
						<div class="clear"> </div>

				</ul>
	
				<div class="clear"> </div>
					
			</form>
			
		</div>
		
	</body>
</html>