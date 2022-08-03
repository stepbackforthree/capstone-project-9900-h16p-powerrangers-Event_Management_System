import styled from 'styled-components';
import { Button, message, Steps, Result, Image, Radio, Spin,Input  } from 'antd';
import React, { useState, useEffect } from 'react';
import QRcode from '../../img/paymentQRcode.png';
import AttachMoneyIcon from '@material-ui/icons/AttachMoney';
import request from '../../utils/request';

import './styles.css';
const { Step } = Steps;
const steps = [
  {
    title: 'Choose Payment Method',
    content: 'First-content',
  },
  {
    title: 'Recharge',
    content: 'Second-content',
  },
  {
    title: 'Success!',
    content: 'Last-content',
  },
];

const TitleContainer = styled.div`
  /* position: relative; */
  display: flex;
  align-items: center;
  /* justify-content: space-between; */
  /* padding: 10px; */
  border-bottom: 1px solid rgba(0,0,0,.26);
  padding: 1.5rem 1rem;
`;

const ContentContainer = styled.div`
  display: flex;
  flex-grow: 1;
  flex-direction: column;
  align-items: center;
  padding: 30px;
  width: 100%;
`;

export default function PaymentPage() {
  const [current, setCurrent] = useState(0);
  const [loading, setLoading] = useState(true);
  const [rechargeAmount, setRechargeAmount] = useState(0);

  const next = () => {
    if (current === 1) {
      setTimeout(() => {
      setCurrent(current + 1);
      const data = {
        "rechargeAmount": rechargeAmount
      }
      console.log(data);
      }, 5000);
    } else {
      setCurrent(current + 1);
    }
  };

  const prev = () => {
    setCurrent(current - 1);
  };

  const [value, setValue] = useState(1);

  const onChange = (e) => {
    console.log('radio checked', e.target.value);
    setValue(e.target.value);
  };


  return (
    <>
      <TitleContainer>
        <AttachMoneyIcon/><h2>Recharge Your Wallet</h2>
      </TitleContainer>
      <ContentContainer>
        <Steps current={current}>
          {steps.map((item) => (
            <Step key={item.title} title={item.title} />
          ))}
        </Steps>
        <div className="steps-content">
          {steps[current].content === 'First-content' && 
            <>
              <h2>Choose your recharge payment method:</h2>
              <Radio.Group onChange={onChange} value={value}>
                <Radio value={1}>Wechat</Radio>
                <Radio value={2}>Alipay</Radio>
                <Radio value={3}>Paypal</Radio>
                <Radio value={4}>Bpay</Radio>
              </Radio.Group>
              <h2>Input the amount to recharge:</h2>
              <Input 
                allowClear
                prefix="$" 
                type="number" 
                suffix="Dollars" 
                value={rechargeAmount}
                onChange={(e) => setRechargeAmount(e.target.value)}
                style={{"width": "25%"}}
              />
            </> 
          }
          {steps[current].content === 'Second-content' && 
            <div>
              <h3>Scan the QR code below to pay</h3>
              <Spin spinning={loading} delay={10000}>
                <Image
                  width={250}
                  src={QRcode}
                />
              </Spin>
              
            </div> 
          }
          {steps[current].content === 'Last-content' && 
            <div>
              <Result
              status="success"
              title="Successfully Recharge Your Wallet!!"
              subTitle="Order number: 202208041344456 configuration takes 1-3 minutes, please wait."
              extra={[
                <Button type="primary" key="console">
                  Go to Join Event!
                </Button>,
                <Button key="buy">
                  Recharge More
                </Button>,
              ]}
            />
            </div>
          }
        </div>
        <div className="steps-action">
          {current < steps.length - 1 && (
            <Button type="primary" onClick={() => next()}>
              Next
            </Button>
          )}
          {current === steps.length - 1 && (
            <Button type="primary" onClick={() => message.success('Processing complete!')}>
              Done
            </Button>
          )}
          {current > 0 && (
            <Button
              style={{
                margin: '0 8px',
              }}
              onClick={() => prev()}
            >
              Previous
            </Button>
          )}
        </div>
      </ContentContainer>
    </>
  );
}
