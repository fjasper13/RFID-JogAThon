var express = require('express')
    , app = module.exports = express();
 
// Using the .html extension instead of
// having to name the views as *.ejs
app.engine('.ejs', require('ejs').__express);
 
// Set the folder where the pages are kept
app.set('views', __dirname + '/public');
 
// This avoids having to provide the 
// extension to res.render()
app.set('view engine', 'ejs');

//var express = require('express');
//var app = express();
var bodyParser = require('body-parser'); 
var mysql = require('mysql');
var cookieParser = require('cookie-parser')
//var login = require('./routes/loginroutes');
//var showdb = require('./routes/showdb');
//var invest = require('./routes/invest');



var connection = mysql.createConnection({
  host     : 'localhost',
  user     : 'root',
  password : '',
  database : 'jogathon'
});

connection.connect(function(err){
if(!err) {
    console.log("Database is connected ... ");
} else {
    console.log("Error connecting database ... ");
}



});

// Create application/x-www-form-urlencoded parser
var urlencodedParser = bodyParser.urlencoded({ extended: false }) 

app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());

app.use(function(req, res, next) {
    res.header("Access-Control-Allow-Origin", "*");
    res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
    next();
});

var router = express.Router();
// test route
router.get('/', function(req, res) {
    res.json({ message: 'welcome to our upload module apis' });
});

//route to handle user registration
//router.post('/register',login.register);
//router.post('/login',login.login);
//router.post('/search', showdb.show);
//app.use('/api', router);


app.use(express.static(__dirname + '/public'));


app.get('/index.html', function (req, res) {
	res.sendFile( __dirname + "/public" + "/index.html" );
  // console.log("Cookie: ", req.cookies)
});

/* app.get('/ui_regist.html', function (req, res) {
 res.sendFile( __dirname + "/" + "ui_regist.html" );
}); */

app.post('/register', urlencodedParser, function (req, res) {
 response = {
  nama_lengkap: req.body.name,
  gender: req.body.gender,
	donate: req.body.donate,
  category: req.body.category,
	donate_tetap: "500000",
 };
 console.log(response);
 
 if (response.category != "guru") {
	 var sql = "INSERT INTO registration (Nama, Gender, Kategori, Donasi_Lap) VALUES ('"+response.nama_lengkap+"', '"+response.gender+"', '"+response.category+"', '"+response.donate+"')";
 } else {
	 var sql = "INSERT INTO registration (Nama, Gender, Kategori, Donasi_Lap, Donasi_Tetap) VALUES ('"+response.nama_lengkap+"', '"+response.gender+"', '"+response.category+"', '"+response.donate+"', '"+response.donate_tetap+"')";
 }
 
 
 connection.query(sql, function(err) {
  if (err) throw err;
   console.log("1 record inserted");
 });
 res.sendFile( __dirname + "/public" + "/register.html" );
});

var server = app.listen(8081, function () {
	var host = server.address().address
	var port = server.address().port
	console.log("Form POST Example - app listening at http://%s:%s", host, port)
})

