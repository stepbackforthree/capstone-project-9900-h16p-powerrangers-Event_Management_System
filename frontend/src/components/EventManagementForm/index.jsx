import React, { useEffect, useState } from 'react';
import Avatar from '@material-ui/core/Avatar';
import CssBaseline from '@material-ui/core/CssBaseline';
import TextField from '@material-ui/core/TextField';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import Checkbox from '@material-ui/core/Checkbox';
import Link from '@material-ui/core/Link';
import Grid from '@material-ui/core/Grid';
import Box from '@material-ui/core/Box';
import LockOutlinedIcon from '@material-ui/icons/LockOutlined';
import Typography from '@material-ui/core/Typography';
import { makeStyles } from '@material-ui/core/styles';
import Container from '@material-ui/core/Container';
import { useForm, Form } from '../useForm';
import EditIcon from '@material-ui/icons/Edit';
import Paper from '@material-ui/core/Paper';
import EditLocationIcon from '@material-ui/icons/EditLocation';
import './styles.css';
import moment from 'moment';
import { Button, Modal, Select,Image, message, Card, Input, Switch, DatePicker  } from 'antd';
import request from '../../utils/request';
import { useSearchParams } from 'react-router-dom';
import { CheckOutlined, CloseOutlined } from '@ant-design/icons';



const { Option } = Select;
const { RangePicker } = DatePicker;


function Copyright() {
  return (
    <Typography variant="body2" color="textSecondary" align="center">
      {'Copyright © '}
      <Link color="inherit" href="#">
        9900-H16P-PowerRangers
      </Link>{' '}
      {new Date().getFullYear()}
      {'.'}
    </Typography>
  );
}



const useStyles = makeStyles((theme) => ({
  paper: {
    marginTop: theme.spacing(8),
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
  },
  avatar: {
    margin: theme.spacing(1),
    // backgroundColor: theme.palette.secondary.main,
    backgroundColor: theme.palette.primary.light,
  },
  submit: {
    margin: theme.spacing(1, 0, 2),
  },
}));

const ifDisplay = [
  {
    value: true,
    label: 'online',
  },
  {
    value: false,
    label: 'offline',
  },
];


const eventType = [
  {
    value: 1,
    label: 'concert',
  },
  {
    value: 2,
    label: 'Sports',
  },
  {
    value: 3,
    label: 'Comic and Animation',
  },
  {
    value: 4,
    label: 'parents-child campaign',
  },
  {
    value: 5,
    label: 'Tourism exhibition',
  },
];

const couponTypeList = [
  "vouchar",
  "full discount coupon"
]

const location = [
  {
    value: 'Sydney',
    label: 'Sydney',
  },
  {
    value: 'Melbourne',
    label: 'Melbourne',
  },
  {
    value: 'Queensland',
    label: 'Queensland',
  },
];

const eventTypeMap = {
  1: 'Concert',
  2: 'Sports',
  3: 'Comic and Animation',
  4: 'Parents-Child Campaign',
  5: 'Tourism Exhibition'
}

const ticketType = {
  "fullPriceTicket": "fullPriceTicket",
  "studentTicket": "studentTicket",
  "infieldTicket": "infieldTicket",
  "standTicket": "standTicket",
  "earlyBirdTicket": "earlyBirdTicket"
}


export default function EventManagementForm(props) {
  const [isModal1Visible, setIsModal1Visible] = useState(false);
  const [isModal2Visible, setIsModal2Visible] = useState(false);
  const [isModalTypeVisible, setIsModalTypeVisible] = useState(false);
  const [isModalAddTicketVisible, setIsModalAddTicketVisible] = useState(false);
  const [isModalAddCouponVisible, setIsModalAddCouponVisible] = useState(false);
  const [editUrl, setEditUrl] = useState('');
  const [inputType, setInputType] = useState('text');
  const [inputKey, setInputKey] = useState('');
  const [inputValue, setInputValue] = useState('');
  const [locationValue, setLocationValue] = useState('');
  const [typeValue, setTypeValue] = useState('');
  const [ticketTypeValue, setTicketTypeValue] = useState('');
  const [ticketPriceValue, setTicketPriceValue] = useState('');
  const [ticketAmountValue, setTicketAmountValue] = useState('');

  const [search,setSearch] = useSearchParams();
  const eventId = search.get("eventId");
  const eventName = search.get("eventName");
  const [couponList, setCouponList] = useState();
  

  // text input modal
  const showModal1 = () => {
    setIsModal1Visible(true);
  };
  const handleOk1 = () => {
    console.log('submit.....');
    console.log(editUrl);
    console.log(inputValue);
    console.log('/'+editUrl);
    console.log(inputKey);
    const data = {
      'eventName': eventName,
      [inputKey]: inputValue
    }
    console.log('data:', data);
    request('/events/'+editUrl, {
      method: 'POST',
      data: data
    }).then(data => {
      console.log('data:', data);
    })
    setIsModal1Visible(false);
    window.location.href = '/host/eventList';
  };
  const handleCancel1 = () => {
    setIsModal1Visible(false);
  };

  // location selection modal
  const showModal2 = () => {
    setIsModal2Visible(true);
  };
  const handleOk2 = () => {
    console.log('submit.....');
    console.log(editUrl);
    console.log(locationValue);
    console.log('/'+editUrl);
    const data = {
      'eventName': eventName,
      'newString': locationValue
    }
    console.log('data:', data);
    request('/events/'+editUrl, {
      method: 'POST',
      data: data
    }).then(data => {
      console.log('data:', data);
    })
    setIsModal2Visible(false);
    window.location.href = '/host/eventList';
  };
  const handleCancel2 = () => {
    setIsModal2Visible(false);
  };

  // type selection modal
  const showModalType = () => {
    setIsModalTypeVisible(true);
  };
  const handleOkType = () => {
    console.log('submit.....');
    console.log(editUrl);
    console.log(typeValue);
    console.log('/'+editUrl);
    const data = {
      'eventName': eventName,
      'newInteger': parseInt(typeValue)
    }
    console.log('data:', data);
    request('/events/'+editUrl, {
      method: 'POST',
      data: data
    }).then(data => {
      console.log('data:', data);
    })
    setIsModalTypeVisible(false);
    window.location.href = '/host/eventList';
  };
  const handleCancelType = () => {
    setIsModalTypeVisible(false);
  };
  
  // text input modal
  const showModalAddTicket = () => {
    setIsModalAddTicketVisible(true);
  };
  const handleOkAddTicket = () => {
    console.log(ticketTypeValue, ticketPriceValue, ticketAmountValue);
    const data = {
      'eventName': eventName,
      'ticketType': ticketTypeValue,
      'ticketPrice': ticketPriceValue,
      'ticketAmount': ticketAmountValue
    }
    console.log('data:', data);
    request('/tickets/addTicketType', {
      method: 'POST',
      data: data
    }).then(data => {
      console.log('data:', data);
    })
    setIsModalTypeVisible(false);
    message.success('Add Successfully!');
    setIsModalAddTicketVisible(false);
    setTimeout(() => {
      window.location.reload();
    }, 2000)
    // window.location.href = '/host/eventList';
  };
  const handleCancelAddTicket = () => {
    setIsModalAddTicketVisible(false);
  };



  useEffect(() => {
    console.log('editUrl:',editUrl)
  },[editUrl])

  useEffect(() => {
    console.log('inputType:',inputType)
  },[inputType])

  useEffect(() => {
    console.log('inputValue:',inputValue)
  },[inputValue])

  useEffect(() => {
    console.log('typeValue:',typeValue)
  },[typeValue])

  const classes = useStyles();
  // const { values, setValues, handleInputChange } = useForm(event);

  const event = props.event;
  const handleSubmit = () => {
    console.log('submit success');
    // props.submit({
    //   ...values,
    // });
  }

  // upload img
  const uploadIamge = async (e) => {
    const file = e.target.files[0];
    const base64 = await convertBase64(file);
    // setValues({
    //   ...values,
    //   image: base64,
    // })
  }

  const convertBase64 = (file) => {
    return new Promise((resolve, reject) => {
      const fileReader = new FileReader();
      fileReader.readAsDataURL(file);

      fileReader.onload = () => {
        resolve(fileReader.result);
      };

      fileReader.onerror = (error) => {
        reject(error);
      }
    })
  }

  const handleInputChange = (e) => {
    console.log('input change:', e.target.value);
    setInputValue(e.target.value);
  }

  const handleLocationChange = (value) => {
    setLocationValue(value);
    console.log(`selected ${value}`);
  };

  const handleTypeChange = (value) => {
    setTypeValue(value);
    console.log(`selected ${value}`);
  };

  const handleTicketPriceChange = (e) => {
    setTicketPriceValue(e.target.value);
  }

  const handleTicketAmountChange = (e) => {
    setTicketAmountValue(e.target.value);
  }


  // coupon
  useEffect(() => {
    request(`/coupon/getCoupons?eventId=${eventId}`, {
      method: 'GET'
    }).then((response) => {
      console.log(response);
      setCouponList(response);
    })
  }, [])

  const showTime = (string) => {
    const time = string.split('.')[0];
    const timeStr = time.split('T')[0] + ' ' + time.split('T')[1];
    return timeStr;
  }

  // text input modal
  const showModalAddCoupon = () => {
    setIsModalAddCouponVisible(true);
  };
  const handleOkAddCoupon = () => {
    if (couponTypeValue === "1") {
      setCouponTypeValue(1);
      setCouponsTresholdValue(0);
    }
    if (couponTypeValue === "2") {
      setCouponTypeValue(2);
    }
    const data = {
      "couponType": couponTypeValue,
      "couponName": couponCodeValue,
      "amount": couponsAmountValue,
      "threshold": couponsTresholdValue,
      "isVisible": isCouponVisible,
      "money": couponsMoneyValue,
      "startTime": couponStartTime,
      "endTime": couponEndTime,
      "eventId": eventId
    }
    console.log(data);
    request('/coupon/addCoupon', {
      method: 'POST',
      data: data
    }).then((response) => {
      console.log(response);
    })
    setIsModalAddCouponVisible(false);
    message.success('Add Successfully!');
    setTimeout(() => {
      window.location.reload();
    }, 2000)
  };
  const handleCancelAddCoupon = () => {
    setIsModalAddCouponVisible(false);
  };

  const [couponTypeValue, setCouponTypeValue] = useState();
  const [couponCodeValue, setCouponCodeValue] = useState('');
  const [couponsAmountValue, setCouponsAmountValue] = useState();
  const [couponsTresholdValue, setCouponsTresholdValue] = useState();
  const [couponsMoneyValue, setCouponsMoneyValue] = useState();
  const [isCouponVisible, setIsCouponVisible] = useState(false);
  const [couponStartTime, setCouponStartTime] = useState('');
  const [couponEndTime, setCouponEndTime] = useState('');


  const onTimeChange = (value, dateString) => {
    console.log('Selected Time: ', value);
    console.log('Formatted Selected Time: ', dateString);
    const startTime = dateString[0].split(' ')[0] + 'T' + dateString[0].split(' ')[1];
    const endTime = dateString[1].split(' ')[0] + 'T' + dateString[1].split(' ')[1];
    setCouponStartTime(startTime);
    setCouponEndTime(endTime);
  };

  const onTimeOk = (value) => {
    console.log('onOk: ', value);
  };


  return (
    <>
      <Modal title="Text Input" visible={isModal1Visible} onOk={handleOk1} onCancel={handleCancel1}>
        <b>{editUrl}:</b>
        <input type={inputType} value={inputValue} onChange={handleInputChange}/>
      </Modal>

      <Modal title="Location Update" visible={isModal2Visible} onOk={handleOk2} onCancel={handleCancel2}>
        <b>{editUrl}:</b>
        <Select
          value={locationValue}
          style={{
            width: 150,
          }}
          onChange={handleLocationChange}
        >
          <Option value="Sydney">Sydney</Option>
          <Option value="Melbourne">Melbourne</Option>
          <Option value="Queensland ">Queensland </Option>
        </Select>
      </Modal>

      <Modal title="Type Update" visible={isModalTypeVisible} onOk={handleOkType} onCancel={handleCancelType}>
        <b>{editUrl}:</b>
        <Select
          value={typeValue}
          style={{
            width: 200,
          }}
          onChange={setTypeValue}
        >
          <Option value="1">Concert</Option>
          <Option value="2">Sports</Option>
          <Option value="3">Comic and Animation</Option>
          <Option value="4">Parents-Child Campaign</Option>
          <Option value="5">Tourism Exhibition</Option>
        </Select>
      </Modal>

      <Modal title="Add Ticket Type" visible={isModalAddTicketVisible} onOk={handleOkAddTicket} onCancel={handleCancelAddTicket}>
        <div className="add-ticket-type-modal">
          <b>Choose Ticket Type:</b>
          <Select
            value={ticketTypeValue}
            style={{
              width: 200,
            }}
            onChange={setTicketTypeValue}
          >
            <Option value="full price ticket">Full Price Ticket</Option>
            <Option value="student ticket">Student Ticket</Option>
            <Option value="infield ticket">Infield Ticket</Option>
            <Option value="stand ticket">Stand Ticket</Option>
            <Option value="early bird ticket">Early Bird Ticket</Option>
          </Select>
        </div>
        <div className="add-ticket-type-modal">
          <b>Choose Ticket Price:</b>
          <input type='number' value={ticketPriceValue} onChange={handleTicketPriceChange}/>
        </div>
        <div className="add-ticket-type-modal">
          <b>Choose Ticket Amount:</b>
          <input type='number' value={ticketAmountValue} onChange={handleTicketAmountChange}/>
        </div>
      </Modal>

      <Modal title="Add Coupon" visible={isModalAddCouponVisible} onOk={handleOkAddCoupon} onCancel={handleCancelAddCoupon}>
        <div className="add-ticket-type-modal">
          <b>Choose Coupon Type:</b>
          <Select
            value={couponTypeValue}
            style={{
              width: 180,
            }}
            onChange={setCouponTypeValue}
          >
            <Option value="1">vouchar</Option>
            <Option value="2">full discount coupon</Option>
          </Select>
        </div>
        <div className="add-ticket-type-modal">
          <b>Set Coupon Code:</b>
          <Input value={couponCodeValue} onChange={(e) => {setCouponCodeValue(e.target.value)}}/>
        </div>
        <div className="add-ticket-type-modal">
          <b>Set Number of Coupons:</b>
          <Input type='number' value={couponsAmountValue} onChange={(e)=>{setCouponsAmountValue(e.target.value)}}/>
        </div>
        <div className="add-ticket-type-modal">
          <b>Set Treshold of Coupons:</b>
          <Input prefix="$" type='number' value={couponsTresholdValue} onChange={(e)=>{setCouponsTresholdValue(e.target.value)}}/>
        </div>
        <div className="add-ticket-type-modal">
          <b>Set Discount of Coupons:</b>
          <Input prefix="$" type='number' value={couponsMoneyValue} onChange={(e)=>{setCouponsMoneyValue(e.target.value)}}/>
        </div>
        <div className="add-ticket-type-modal">
          <b>Visiblity(whether customer can see or not):</b>    
          <Switch
            checkedChildren={<CheckOutlined />}
            unCheckedChildren={<CloseOutlined />}
            checked={isCouponVisible}
            onChange={(value) => {setIsCouponVisible(value)}}
          />
        </div>
        <div className="add-ticket-type-modal">
          <b>Period of Validity :</b>    
          <RangePicker
            showTime={{
              format: 'HH:mm:ss',
            }}
            format="YYYY-MM-DD HH:mm:ss"
            onChange={onTimeChange}
            onOk={onTimeOk}
          />
        </div>





      </Modal>




      <Container component="main" maxWidth="xs">
        <CssBaseline />
        <div className={classes.paper}>
          <Avatar className={classes.avatar}>
            <EditIcon />
          </Avatar>
          <Typography component="h1" variant="h5">
            Event Edit Management
          </Typography>


          <Paper elevation={3} className="details">
            <div className="details-row">
              <div>
                <b>Event Name:</b>
                {event.eventName}
              </div>
              <div>
                <EditLocationIcon/>{event.location}
              </div>
            </div>

            <div className="details-row">
              <Button type="link" onClick={() => {
                showModal1();
                setEditUrl('updateEventName');
                setInputType('text');
                setInputKey('newString');
                setInputValue(event.eventName);
              }}>
                Edit Event Name
              </Button>
              <Button type="link" onClick={() => {
                showModal2();
                setEditUrl('updateEventAddress');
                setLocationValue(event.location);
              }}>
                Edit Location
              </Button>
            </div>
            <div className="details-row">
              <div>
                <div>
                  <b>Start Time:</b>
                </div>
                <div>
                  {moment(event.startTime).format("YYYY-MM-DD HH:mm:ss")}
                </div>
              </div>
              <i style={{"padding": "0 20px"}}>To</i>
              <div>
                <div>
                  <b>End Time:</b>
                </div>
                <div>
                  {moment(event.endTime).format("YYYY-MM-DD HH:mm:ss")}
                </div>
              </div>
            </div>
            <div className="details-one-center">
              <b>Image:</b>
              <Image
                width={200}
                src={event.image}
              />
            </div>
            <div className="details-row">
              <div>
                <b>Event Type:</b>
                {eventTypeMap[event.eventType]}
              </div>
              <Button type="link" onClick={() => {
                showModalType();
                setEditUrl('updateEventType');
                setTypeValue(event.eventType.toString());
                console.log(typeof (event.eventType.toString()));
                console.log(event.eventType.toString());
              }}>
                Edit Event Type
              </Button>
            </div>
            <div className="details-row">
              <div>
                <b>Event Description:</b>
                {event.description}
              </div>
              <Button type="link" onClick={() => {
                showModal1();
                setEditUrl('updateEventDescription');
                setInputType('text');
                setInputKey('newString');
                setInputValue(event.description);
              }}>
                Edit Description
              </Button>
            </div>
            <div className="details-one">
              <b>If Display this Event:</b>
              {event.isDisplayed ? <>Yes</> : <>No</>}
            </div>
            <div className="details-one">
              <b>Event Cancelled:</b>
              {event.isCancelled ? <>Yes</> : <>No</>}
            </div>
            <div className="details-one">
              <span><b>Tickets Detail:</b></span>
              <Button type="primary" size="small" onClick={() => {
                showModalAddTicket();
                setEditUrl('addTicketType');
              }}>
                add ticket type
              </Button>
            </div>

            {event.tickets && event.tickets.map((item) => {
              return (
                <div className="details-row" key={item.ticketType}>
                  <div>{item.ticketType}:<i>${item.ticketPrice}</i></div>
                  <div><b>Amount:</b>{item.ticketAmount}</div>
                </div>
              )
            })}

            <div className="details-one">
              <span><b>Coupon Detail:</b></span>
              <Button 
                type="primary" 
                size="small" 
                onClick={() => {
                showModalAddCoupon();
              }}>
                add Coupon
              </Button>
            </div>

            {couponList && couponList.map((item) => {
              return (
                <Card
                  key={item.couponId}
                  size="small"
                  title={<><b>Type:</b>{couponTypeList[item.couponType-1]}</>}
                  extra={<a href="#">Edit</a>}
                  // style={{
                  //   width: 300,
                  // }}
                >
                  <div><b>Coupon Code: </b>{item.couponName}</div>
                  <div><b>Credit Amount: </b>{item.money}</div>
                  <div><b>Minimum Requirement: </b>{item.threshold}</div>
                  <div><b>Valid Time Period: </b></div>
                  <div>{showTime(item.startTime)} - {showTime(item.endTime)}</div>
                </Card>
              )
            })}

          </Paper>
        </div>
        <Box mt={8}>
          <Copyright />
        </Box>
      </Container>
    </>
  )
}


// {
//   "amount": 0,
//   "couponName": "string",
//   "couponType": 1,
//   "threshold": 0,
//   "eventId": 0,
//   "isVisible": true,
//   "money": 0,
//   "startTime": {
//   },
//   "endTime": {
//   }
// }