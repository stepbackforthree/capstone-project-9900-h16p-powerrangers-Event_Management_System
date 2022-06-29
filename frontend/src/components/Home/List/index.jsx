import ListItem from './ListItem';
import React from 'react';
import './styles.css';
// import Card from '../../Card';

export default function List({list}) {
  return (
    <div className="list-wrap">
    {list.map(item => <ListItem key={item.id} item={item} />)}
    </div>
  )
}
