import React, { useEffect, useState } from 'react';
import './App.css';
import { UserTable } from './UserTable';
import { Container, Header, Message } from 'semantic-ui-react';
import axios from 'axios';
import Loader from './Loader';

export interface User {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  password: string;
}

const App: React.FC = () => {
  // const [data, setData] = useState<Array<User> | Error>();
  // useEffect(() => {
  //   fetch('http://localhost:9080/resources/books')
  //     .then((response) => response.json() as Promise<User[]>)
  //     .then((data) => setData(data))
  //     .catch((error) => setData(new Error(error.statusText)));
  // }, []);

  const [data, setData] = useState<any>();

  useEffect(() => {
    // const config = {
    //   header: {
    //     'Access-Control-Allow=Origin': '*',
    //   },
    // };
    try {
      const getRes = async () => {
        const res = await axios.get('http://localhost:9080/api/users');
        console.log(typeof res);
        setData(res);
      };
      getRes();
    } catch (err) {
      console.error(err);
    }
  });

  let content: JSX.Element;

  if (!data) {
    content = <Loader />;
  } else if (data instanceof Error) {
    content = (
      <Message negative>An error occurred while fetching the data</Message>
    );
  } else {
    content = <UserTable users={data}></UserTable>;
  }

  return (
    <Container>
      <Header as='h2'>Available Books</Header>
      {content}
    </Container>
  );
};

export default App;
