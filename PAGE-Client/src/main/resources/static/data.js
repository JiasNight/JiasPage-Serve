const reportData = {
        "reportId": 1,
        "regionId": 1,
        "footer": {
            "C_submit_time": null,
            "C_charge": null,
            "C_preparer": null
        },
        "reportCode": "a406",
        "header": {
            "C_organ_unit": null,
            "C_report_period": null
        },
        "body": [
            {
                "quotaId": 10,
                "quotaName": "一、畜禽存栏",
                "parentId": 0,
                "level": 1,
                "index": 1,
                "quotaUnit": null,
                "quotaCode": null,
                "entryColumns": [
                    {
                        "isInput": false,
                        "isCheck": 0,
                        "type": "2",
                        "length": "10",
                        "key": null,
                        "value": null,
                        "checkScript": null
                    }
                ],
                "children": [
                    {
                        "quotaId": 1001,
                        "quotaName": "猪",
                        "parentId": 10,
                        "level": 2,
                        "index": 1,
                        "quotaUnit": null,
                        "quotaCode": "01",
                        "entryColumns": [
                            {
                                "isInput": true,
                                "isCheck": 0,
                                "type": "int",
                                "length": "10",
                                "key": "C_01",
                                "value": null,
                                "checkScript": null
                            }
                        ],
                        "children": [
                            {
                                "quotaId": 100101,
                                "quotaName": "其中：能繁殖母猪",
                                "parentId": 1001,
                                "level": 3,
                                "index": 1,
                                "quotaUnit": null,
                                "quotaCode": "02",
                                "entryColumns": [
                                    {
                                        "isInput": true,
                                        "isCheck": 0,
                                        "type": "decimal",
                                        "length": "10",
                                        "key": "C_02",
                                        "value": null,
                                        "checkScript": null
                                    }
                                ],
                                "children": null
                            }
                        ]
                    },
                    {
                        "quotaId": 1002,
                        "quotaName": "牛",
                        "parentId": 10,
                        "level": 2,
                        "index": 2,
                        "quotaUnit": null,
                        "quotaCode": "03",
                        "entryColumns": [
                            {
                                "isInput": true,
                                "isCheck": 0,
                                "type": "int",
                                "length": "10",
                                "key": "C_03",
                                "value": null,
                                "checkScript": null
                            }
                        ],
                        "children": [
                            {
                                "quotaId": 100201,
                                "quotaName": "其中：肉牛",
                                "parentId": 1002,
                                "level": 3,
                                "index": 1,
                                "quotaUnit": null,
                                "quotaCode": "04",
                                "entryColumns": [
                                    {
                                        "isInput": true,
                                        "isCheck": 0,
                                        "type": "int",
                                        "length": "10",
                                        "key": "C_04",
                                        "value": null,
                                        "checkScript": null
                                    }
                                ],
                                "children": null
                            },
                            {
                                "quotaId": 100202,
                                "quotaName": "奶牛",
                                "parentId": 1002,
                                "level": 3,
                                "index": 2,
                                "quotaUnit": null,
                                "quotaCode": "05",
                                "entryColumns": [
                                    {
                                        "isInput": true,
                                        "isCheck": 0,
                                        "type": "int",
                                        "length": "10",
                                        "key": "C_05",
                                        "value": null,
                                        "checkScript": null
                                    }
                                ],
                                "children": null
                            }
                        ]
                    },
                    {
                        "quotaId": 1003,
                        "quotaName": "羊",
                        "parentId": 10,
                        "level": 2,
                        "index": 3,
                        "quotaUnit": null,
                        "quotaCode": "06",
                        "entryColumns": [
                            {
                                "isInput": true,
                                "isCheck": 0,
                                "type": "int",
                                "length": "10",
                                "key": "C_06",
                                "value": null,
                                "checkScript": null
                            }
                        ],
                        "children": [
                            {
                                "quotaId": 100301,
                                "quotaName": "1.山羊",
                                "parentId": 1003,
                                "level": 3,
                                "index": 1,
                                "quotaUnit": null,
                                "quotaCode": "07",
                                "entryColumns": [
                                    {
                                        "isInput": true,
                                        "isCheck": 0,
                                        "type": "int",
                                        "length": "10",
                                        "key": "C_07",
                                        "value": null,
                                        "checkScript": null
                                    }
                                ],
                                "children": null
                            },
                            {
                                "quotaId": 100302,
                                "quotaName": "2.绵羊",
                                "parentId": 1003,
                                "level": 3,
                                "index": 2,
                                "quotaUnit": null,
                                "quotaCode": "08",
                                "entryColumns": [
                                    {
                                        "isInput": true,
                                        "isCheck": 0,
                                        "type": "int",
                                        "length": "10",
                                        "key": "C_08",
                                        "value": null,
                                        "checkScript": null
                                    }
                                ],
                                "children": null
                            }
                        ]
                    },
                    {
                        "quotaId": 1004,
                        "quotaName": "活家禽",
                        "parentId": 10,
                        "level": 2,
                        "index": 4,
                        "quotaUnit": null,
                        "quotaCode": "09",
                        "entryColumns": [
                            {
                                "isInput": true,
                                "isCheck": 0,
                                "type": "int",
                                "length": "10",
                                "key": "C_09",
                                "value": null,
                                "checkScript": null
                            }
                        ],
                        "children": [
                            {
                                "quotaId": 100401,
                                "quotaName": "其中：活鸡",
                                "parentId": 1004,
                                "level": 3,
                                "index": 1,
                                "quotaUnit": null,
                                "quotaCode": "10",
                                "entryColumns": [
                                    {
                                        "isInput": true,
                                        "isCheck": 0,
                                        "type": "int",
                                        "length": "10",
                                        "key": "C_10",
                                        "value": null,
                                        "checkScript": null
                                    }
                                ],
                                "children": [
                                    {
                                        "quotaId": 10040101,
                                        "quotaName": "其中：肉鸡",
                                        "parentId": 100401,
                                        "level": 4,
                                        "index": 1,
                                        "quotaUnit": null,
                                        "quotaCode": "11",
                                        "entryColumns": [
                                            {
                                                "isInput": true,
                                                "isCheck": 0,
                                                "type": "int",
                                                "length": "10",
                                                "key": "C_11",
                                                "value": null,
                                                "checkScript": null
                                            }
                                        ],
                                        "children": null
                                    },
                                    {
                                        "quotaId": 10040102,
                                        "quotaName": "蛋鸡",
                                        "parentId": 100401,
                                        "level": 4,
                                        "index": 2,
                                        "quotaUnit": null,
                                        "quotaCode": "12",
                                        "entryColumns": [
                                            {
                                                "isInput": true,
                                                "isCheck": 0,
                                                "type": "int",
                                                "length": "10",
                                                "key": "C_12",
                                                "value": null,
                                                "checkScript": null
                                            }
                                        ],
                                        "children": null
                                    }
                                ]
                            }
                        ]
                    }
                ]
            },
            {
                "quotaId": 20,
                "quotaName": "二、畜禽出栏",
                "parentId": 0,
                "level": 1,
                "index": 2,
                "quotaUnit": null,
                "quotaCode": null,
                "entryColumns": [
                    {
                        "isInput": false,
                        "isCheck": 0,
                        "type": null,
                        "length": null,
                        "key": null,
                        "value": null,
                        "checkScript": null
                    }
                ],
                "children": [
                    {
                        "quotaId": 2001,
                        "quotaName": "猪",
                        "parentId": 20,
                        "level": 2,
                        "index": 1,
                        "quotaUnit": null,
                        "quotaCode": "13",
                        "entryColumns": [
                            {
                                "isInput": true,
                                "isCheck": 0,
                                "type": "int",
                                "length": "10",
                                "key": "C_13",
                                "value": null,
                                "checkScript": null
                            }
                        ],
                        "children": null
                    },
                    {
                        "quotaId": 2002,
                        "quotaName": "牛",
                        "parentId": 20,
                        "level": 2,
                        "index": 2,
                        "quotaUnit": null,
                        "quotaCode": "14",
                        "entryColumns": [
                            {
                                "isInput": true,
                                "isCheck": 0,
                                "type": "int",
                                "length": "10",
                                "key": "C_14",
                                "value": null,
                                "checkScript": null
                            }
                        ],
                        "children": null
                    },
                    {
                        "quotaId": 2003,
                        "quotaName": "羊",
                        "parentId": 20,
                        "level": 2,
                        "index": 3,
                        "quotaUnit": null,
                        "quotaCode": "15",
                        "entryColumns": [
                            {
                                "isInput": true,
                                "isCheck": 0,
                                "type": "int",
                                "length": "10",
                                "key": "C_15",
                                "value": null,
                                "checkScript": null
                            }
                        ],
                        "children": [
                            {
                                "quotaId": 200301,
                                "quotaName": "1.山羊",
                                "parentId": 2003,
                                "level": 3,
                                "index": 1,
                                "quotaUnit": null,
                                "quotaCode": "16",
                                "entryColumns": [
                                    {
                                        "isInput": true,
                                        "isCheck": 0,
                                        "type": "int",
                                        "length": "10",
                                        "key": "C_16",
                                        "value": null,
                                        "checkScript": null
                                    }
                                ],
                                "children": null
                            },
                            {
                                "quotaId": 200302,
                                "quotaName": "2.绵羊",
                                "parentId": 2003,
                                "level": 3,
                                "index": 2,
                                "quotaUnit": null,
                                "quotaCode": "17",
                                "entryColumns": [
                                    {
                                        "isInput": true,
                                        "isCheck": 0,
                                        "type": "int",
                                        "length": "10",
                                        "key": "C_17",
                                        "value": null,
                                        "checkScript": null
                                    }
                                ],
                                "children": null
                            }
                        ]
                    },
                    {
                        "quotaId": 2004,
                        "quotaName": "活家禽",
                        "parentId": 20,
                        "level": 2,
                        "index": 4,
                        "quotaUnit": null,
                        "quotaCode": "18",
                        "entryColumns": [
                            {
                                "isInput": true,
                                "isCheck": 0,
                                "type": "int",
                                "length": "10",
                                "key": "C_18",
                                "value": null,
                                "checkScript": null
                            }
                        ],
                        "children": [
                            {
                                "quotaId": 200401,
                                "quotaName": "其中：活鸡",
                                "parentId": 2004,
                                "level": 3,
                                "index": 1,
                                "quotaUnit": null,
                                "quotaCode": "19",
                                "entryColumns": [
                                    {
                                        "isInput": true,
                                        "isCheck": 0,
                                        "type": "int",
                                        "length": "10",
                                        "key": "C_19",
                                        "value": null,
                                        "checkScript": null
                                    }
                                ],
                                "children": null
                            }
                        ]
                    }
                ]
            },
            {
                "quotaId": 30,
                "quotaName": "三、畜禽产品产量",
                "parentId": 0,
                "level": 1,
                "index": 3,
                "quotaUnit": null,
                "quotaCode": null,
                "entryColumns": [
                    {
                        "isInput": false,
                        "isCheck": 0,
                        "type": null,
                        "length": null,
                        "key": null,
                        "value": null,
                        "checkScript": null
                    }
                ],
                "children": [
                    {
                        "quotaId": 3001,
                        "quotaName": "猪肉",
                        "parentId": 30,
                        "level": 2,
                        "index": 1,
                        "quotaUnit": null,
                        "quotaCode": "20",
                        "entryColumns": [
                            {
                                "isInput": true,
                                "isCheck": 0,
                                "type": "int",
                                "length": "10",
                                "key": "C_20",
                                "value": null,
                                "checkScript": null
                            }
                        ],
                        "children": null
                    },
                    {
                        "quotaId": 3002,
                        "quotaName": "牛肉",
                        "parentId": 30,
                        "level": 2,
                        "index": 2,
                        "quotaUnit": null,
                        "quotaCode": "21",
                        "entryColumns": [
                            {
                                "isInput": true,
                                "isCheck": 0,
                                "type": "int",
                                "length": "10",
                                "key": "C_21",
                                "value": null,
                                "checkScript": null
                            }
                        ],
                        "children": null
                    },
                    {
                        "quotaId": 3003,
                        "quotaName": "羊肉",
                        "parentId": 30,
                        "level": 2,
                        "index": 3,
                        "quotaUnit": null,
                        "quotaCode": "22",
                        "entryColumns": [
                            {
                                "isInput": true,
                                "isCheck": 0,
                                "type": "int",
                                "length": "10",
                                "key": "C_22",
                                "value": null,
                                "checkScript": null
                            }
                        ],
                        "children": [
                            {
                                "quotaId": 300301,
                                "quotaName": "1.山羊肉",
                                "parentId": 3003,
                                "level": 3,
                                "index": 1,
                                "quotaUnit": null,
                                "quotaCode": "23",
                                "entryColumns": [
                                    {
                                        "isInput": true,
                                        "isCheck": 0,
                                        "type": "int",
                                        "length": "10",
                                        "key": "C_23",
                                        "value": null,
                                        "checkScript": null
                                    }
                                ],
                                "children": null
                            },
                            {
                                "quotaId": 300302,
                                "quotaName": "2.绵羊肉",
                                "parentId": 3003,
                                "level": 3,
                                "index": 2,
                                "quotaUnit": null,
                                "quotaCode": "24",
                                "entryColumns": [
                                    {
                                        "isInput": true,
                                        "isCheck": 0,
                                        "type": "int",
                                        "length": "10",
                                        "key": "C_24",
                                        "value": null,
                                        "checkScript": null
                                    }
                                ],
                                "children": null
                            }
                        ]
                    },
                    {
                        "quotaId": 3004,
                        "quotaName": "禽肉",
                        "parentId": 30,
                        "level": 2,
                        "index": 4,
                        "quotaUnit": null,
                        "quotaCode": "25",
                        "entryColumns": [
                            {
                                "isInput": true,
                                "isCheck": 0,
                                "type": "int",
                                "length": "10",
                                "key": "C_25",
                                "value": null,
                                "checkScript": null
                            }
                        ],
                        "children": [
                            {
                                "quotaId": 300401,
                                "quotaName": "其中：鸡肉",
                                "parentId": 3004,
                                "level": 3,
                                "index": 1,
                                "quotaUnit": null,
                                "quotaCode": "26",
                                "entryColumns": [
                                    {
                                        "isInput": true,
                                        "isCheck": 0,
                                        "type": "int",
                                        "length": "10",
                                        "key": "C_26",
                                        "value": null,
                                        "checkScript": null
                                    }
                                ],
                                "children": null
                            }
                        ]
                    },
                    {
                        "quotaId": 3005,
                        "quotaName": "禽蛋",
                        "parentId": 30,
                        "level": 2,
                        "index": 5,
                        "quotaUnit": null,
                        "quotaCode": "27",
                        "entryColumns": [
                            {
                                "isInput": true,
                                "isCheck": 0,
                                "type": "int",
                                "length": "10",
                                "key": "C_27",
                                "value": null,
                                "checkScript": null
                            }
                        ],
                        "children": [
                            {
                                "quotaId": 300501,
                                "quotaName": "其中：鸡蛋",
                                "parentId": 3005,
                                "level": 3,
                                "index": 1,
                                "quotaUnit": null,
                                "quotaCode": "28",
                                "entryColumns": [
                                    {
                                        "isInput": true,
                                        "isCheck": 0,
                                        "type": "int",
                                        "length": "10",
                                        "key": "C_28",
                                        "value": null,
                                        "checkScript": null
                                    }
                                ],
                                "children": null
                            }
                        ]
                    },
                    {
                        "quotaId": 3006,
                        "quotaName": "生牛奶",
                        "parentId": 30,
                        "level": 2,
                        "index": 6,
                        "quotaUnit": null,
                        "quotaCode": "29",
                        "entryColumns": [
                            {
                                "isInput": true,
                                "isCheck": 0,
                                "type": "int",
                                "length": "10",
                                "key": "C_29",
                                "value": null,
                                "checkScript": null
                            }
                        ],
                        "children": null
                    }
                ]
            }
        ]
    }

export default reportData;
