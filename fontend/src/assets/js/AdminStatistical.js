import $ from 'jquery';

window.onload = function() {
  var data = [];
  var label = [];
  var dataForDataSets = [];

  $.ajax({
    async : false,
    type : "GET",
    data : data,
    contentType : "application/json",
    url : "http://localhost:8080/laptopshop/api/don-hang/report",
    success : function(data) {
      for (var i = 0; i < data.length; i++) {
        label.push(data[i][0] + "/" + data[i][1]);
        dataForDataSets.push(data[i][2]/1000000);
      }
    },
    error : function(e) {
      alert("Error: ", e);
      console.log("Error", e);
    }
  });

  var canvas = document.getElementById('myChart');


  data = {
    labels : label,
    datasets : [ {
      label : "Tổng giá trị ( Triệu đồng)",
      backgroundColor : "#0000ff",
      borderColor : "#0000ff",
      borderWidth : 2,
      hoverBackgroundColor : "#0043ff",
      hoverBorderColor : "#0043ff",
      data : dataForDataSets,
    } ]
  };
  var option = {
    scales : {
      yAxes : [ {
        stacked : true,
        gridLines : {
          display : true,
          color : "rgba(255,99,132,0.2)"
        }
      } ],
      xAxes : [ {
        barPercentage: 0.5,
        gridLines : {
          display : false
        }
      } ]
    },
    maintainAspectRatio: false,
    legend: {
      labels: {
        // This more specific font property overrides the global property
        fontSize: 20
      }
    }
  };

  var myBarChart = Chart.Bar(canvas, {
    data : data,
    options : option
  });
}