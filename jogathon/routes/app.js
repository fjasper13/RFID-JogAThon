var express = require('express')
    , app = module.exports = express();
 
// Using the .html extension instead of
// having to name the views as *.ejs
app.engine('.html', require('ejs').__express);
 
// Set the folder where the pages are kept
app.set('views', __dirname + '/views');
 
// This avoids having to provide the 
// extension to res.render()
app.set('view engine', 'html');


var mysql      = require('mysql');
var connection = mysql.createConnection({
  host     : 'localhost',
  user     : 'root',
  password : '',
  database : 'investment'
});
connection.connect(function(err){
if(!err) {
    console.log("Database is connected ... nn");
} else {
    console.log("Error connecting database ... nn");
}
});
 
var messages = [
  { name: 'Nathan Explosion', message: 'Dethklok rules' },
  { name: 'William Murderface', message: 'Bass is the heart of the band' },
	{ name: 'Adrey Benaya', message: 'Coba coy' },
  { name: 'Dr. Rockso', message: 'Where is my friend Toki?' }
];
 
// Serve the index page
// app.get('/', function(req, res){
  // res.render('index', {
    // pageTitle: 'EJS Demo',
    // messages: messages
  // });
// });

var obj = {};
exports.invest = function(req, res){
    connection.query('SELECT * FROM equity_crowdfunding', function(err, result) {
        if(err){
            throw err;
        } else {
						//console.log(result);
            obj = {print: result};
						console.log(obj);
            res.render('index', obj);                
        }
    });
};
 