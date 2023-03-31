


var option;

/**
 * 厂商需求率
 */
$.ajax({
    type: "post",
    url: PATH + "/getFoctoryNums",
    contentType:"application/json;charset=utf-8",
    success: function factory(data) {
        if (data.length === 0) {
            alert("厂商需求总数累计有误");
        }else {
            var factoryId = document.getElementById('factoryId');
            var myChart = echarts.init(factoryId);

            var xAxis = new Array(0);
            var series = new Array(0);
            for(let d of data) {
                //get内容需要调确认
                xAxis.push(d.factory);
                series.push(d.total);
            }
            option = {
                xAxis: {
                    type: 'category',
                    data: xAxis
                },
                yAxis: {
                    type: 'value'
                },
                series: [
                    {
                        data: series,
                        type: 'bar'
                    }
                ]
            };
            option && myChart.setOption(option);
        }
    },
    error: function (data) {
        alert("厂商需求率展示有误");
    }
})


/**
 * 厂商开发上线超时率表
 */
$.ajax({
    type: "post",
    url: PATH + "/getRequirementisOvertime",
    contentType:"application/json;charset=utf-8",
    success: function factory(data) {
        if (data.length === 0) {
            alert("厂商开开发上线及时率计算有误");
        }else {
            var factoryOvertime = document.getElementById('factoryOvertime');
            var myChart = echarts.init(factoryOvertime);

            var xAxis = new Array(0);
            var series = new Array(0);
            for(var d in data) {
                //get内容需要调确认
                xAxis.push(d);
                series.push(data[d]);
            }
            option = {
                xAxis: {
                    type: 'category',
                    data: xAxis
                },
                yAxis: {
                    type: 'value'
                },
                series: [
                    {
                        data: series,
                        type: 'bar'
                    }
                ]
            };
            option && myChart.setOption(option);
        }
    },
    error: function (data) {
        alert("厂商需求率展示有误");
    }
})

/**
 * 厂商接口日均调用超过200总数
 */
$.ajax({
    type: "post",
    url: PATH + "/getRequirementsRequestAmount",
    contentType:"application/json;charset=utf-8",
    success: function factory(data) {
        if (data.length === 0) {
            alert("接口日均调用计算有误");
        }else {
            var factoryRequestAmounts = document.getElementById('factoryRequestAmounts');
            var myChart = echarts.init(factoryRequestAmounts);

            option = {

                series: data.map(function (data, idx) {
                    var top = idx * 10;
                    return {
                        type: 'pie',
                        radius: [20, 60],
                        top: top + '%',
                        height: '13.33%',
                        left: 'center',
                        width: 400,
                        itemStyle: {
                            borderColor: '#fff',
                            borderWidth: 1
                        },
                        label: {
                            alignTo: 'edge',
                            minMargin: 5,
                            edgeDistance: 10,
                            lineHeight: 15,
                            rich: {
                                time: {
                                    fontSize: 10,
                                    color: '#999'
                                }
                            }
                        },
                        labelLine: {
                            length: 15,
                            length2: 0,
                            maxSurfaceAngle: 80
                        },

                        data: data
                    };
                })
            };
            option && myChart.setOption(option);
        }
    },
    error: function (data) {
        alert("接口日均调用计算有误");
    }
})


/**
 *  厂商需求相似度
 */
$.ajax({
    type: "post",
    url: PATH + "/getFaRequirementSimilar",
    contentType:"application/json;charset=utf-8",
    success: function factory(data) {
        if (data.length === 0) {
            alert("接口日均调用计算有误");
        }else {
            var factoryRequirementSimilar = document.getElementById('factoryRequirementSimilar');
            var myChart = echarts.init(factoryRequirementSimilar);

            option = {
                legend: {},
                tooltip: {},
                dataset: {
                    source: data
                },
                xAxis: { type: 'category' },
                yAxis: {},
                // Declare several bar series, each will be mapped
                // to a column of dataset.source by default.
                series: [{ type: 'bar' }, { type: 'bar' }, { type: 'bar' }]
            };
            option && myChart.setOption(option);
        }
    },
    error: function (data) {
        alert("接口日均调用计算有误");
    }
})








