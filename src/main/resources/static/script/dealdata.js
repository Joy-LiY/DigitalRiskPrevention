


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
            var myChart = echarts.init(factoryId,'roma',{
                width: 300,
                height: 200
            });

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
                    type: 'value',
                    min: 0,
                    max: 400
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
            var myChart = echarts.init(factoryOvertime,null,{
                width: 300,
                height: 200
            });

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
            var myChart = echarts.init(factoryRequirementSimilar,null,{
                width: 300,
                height: 170
            });

            option = {
                tooltip: {
                    trigger: 'item'
                },
                legend: {
                    orient: 'vertical',
                    left: 'left'
                },
                series: [
                    {
                        name: '相似度高',
                        type: 'pie',
                        radius: '50%',
                        data: [
                            { value: 7, name: '东信' },
                            { value: 0, name: '亚信' },
                            { value: 0, name: '鼎力' },
                            { value: 14, name: '浩鲸' },
                            { value: 9, name: '华为' },
                            { value: 18, name: '浪潮' },
                            { value: 4, name: '直真' }

                        ],
                        emphasis: {
                            itemStyle: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
                ]
            };
            option && myChart.setOption(option);
        }
    },
    error: function (data) {
        alert("接口日均调用计算有误");
    }
})


/**

 const data1 = [{
                name: "东信",
                value: 7
            },{
                name: "亚信",
                value: 0
            },{
                name: "鼎力",
                value: 0
            },{
                name: "浩鲸",
                value: 14
            },{
                name: "华为",
                value: 9
            },{
                name: "浪潮",
                value: 18
            },{
                name: "直真",
                value: 4
            }];
 const data2 = [{
                name: "东信",
                value: 10
            },{
                name: "亚信",
                value: 2
            },{
                name: "鼎力",
                value: 0
            },{
                name: "浩鲸",
                value: 9
            },{
                name: "华为",
                value: 11
            },{
                name: "浪潮",
                value: 16
            },{
                name: "直真",
                value: 2
            }];
 const data3 = [{
                name: "东信",
                value: 130
            },{
                name: "亚信",
                value: 58
            },{
                name: "鼎力",
                value: 25
            },{
                name: "浩鲸",
                value: 134
            },{
                name: "华为",
                value: 20
            },{
                name: "浪潮",
                value: 167
            },{
                name: "直真",
                value: 24
            }];


 option = {
                title: [
                    {
                        subtext: '相似度高',
                        left: '16.67%',
                        top: '5%',
                        textAlign: 'center'
                    },
                    {
                        subtext: '相似度中',
                        left: '50%',
                        top: '5%',
                        textAlign: 'center'
                    },
                    {
                        subtext: '相似度低',
                        left: '83.33%',
                        top: '5%',
                        textAlign: 'center'
                    }
                ],
                series: [
                    {
                        type: 'pie',
                        radius: '10%',
                        center: ['20%', '20%'],
                        data: data1,
                        label: {
                            position: 'outer',
                            alignTo: 'none',
                            bleedMargin: 5
                        },
                        left: 0,
                        right: '66.6667%',
                        top: 0,
                        bottom: 0
                    },
                    {
                        type: 'pie',
                        radius: '10%',
                        center: ['20%', '20%'],
                        data: data2,
                        label: {
                            position: 'outer',
                            alignTo: 'labelLine',
                            bleedMargin: 5
                        },
                        left: '33.3333%',
                        right: '33.3333%',
                        top: 0,
                        bottom: 0
                    },
                    {
                        type: 'pie',
                        radius: '10%',
                        center: ['20%', '20%'],
                        data: data3,
                        label: {
                            position: 'outer',
                            alignTo: 'edge',
                            margin: 20
                        },
                        left: '66.6667%',
                        right: 0,
                        top: 0,
                        bottom: 0
                    }
                ]
            };
 */




