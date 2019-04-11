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

exports.show = function(req,res){
  connection.query('SELECT * FROM equity_crowdfunding', function(err, rows, fields) {
	if (!err) {
		console.log("The solution is:", rows);
		console.log("\n");
		}
	else
		console.log("Error while performing query");
});
}

exports.get = function(req, res){
	var obj = {};
	connection.query('SELECT * FROM users', function(err, result) {

			if(err){
					throw err;
			} else {
					obj = {print: result};
					res.render('print', obj);                
			}
	});
}

var obj = {};
exports.investasi = function(req, res){
    connection.query('SELECT * FROM equity_crowdfunding', function(err, result) {
        if(err){
            throw err;
        } else {
						//console.log(result);
            obj = {print: result};
						console.log(obj);
            res.render('investasi', obj);                
        }
    });
};

