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

app.get('/index2.html', function (req, res) {
	res.sendFile( __dirname + "/public" + "/index2.html" );
  // console.log("Cookie: ", req.cookies)
});

app.post('/register', urlencodedParser, function (req, res) {
 response = {
	id_tag: req.body.id_tag,
	no_peserta: req.body.no_peserta,
	nama_lengkap: req.body.nama_lengkap,
	gender: req.body.gender,
	donate: req.body.donasi,
	category: req.body.category,
 };
 console.log(response);
 
 if (response.category != "1") {
	 var sql = "INSERT INTO registration (tag_ID, no_Peserta, nama, gender, kategori_ID, donasi_lap) VALUES ('"+response.id_tag+"', '"+response.no_peserta+"', '"+response.nama_lengkap+"', '"+response.gender+"', '"+response.category+"', '"+response.donate+"')";
 } else {
	 var sql = "INSERT INTO registration (tag_ID, no_Peserta, nama, gender, kategori_ID, donasi_lap) VALUES ('"+response.id_tag+"', '"+response.no_peserta+"', '"+response.nama_lengkap+"', '"+response.gender+"', '"+response.category+"', '"+response.donate+"')";
 }
 
 connection.query(sql, function(err) {
  if (err) throw err;                                                             
   console.log("1 record inserted");
 });
 res.sendFile( __dirname + "/public" + "/index2.html" );
});

var server = app.listen(8081, function () {
	var host = server.address().address
	var port = server.address().port
	console.log("Form POST Example - app listening at http://%s:%s", host, port)
})

