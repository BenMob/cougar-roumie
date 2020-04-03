
/*
        TEST DATA
        These queries will prefill the database with test data
*/

-- Prefill public.users table
-- COLUMN LAYOUT: id, active, answer_id, email, first_name, gender, last_name, password, user_name
/*
INSERT INTO public.users VALUES (1, TRUE, 10, 'email@email.com', 'firstname', 1, 'lastname', 'password', 'user1');
INSERT INTO public.users VALUES (2, TRUE, 11, 'email@email.com', 'firstname', 2, 'lastname', 'password', 'user2');
*/
/*
INSERT INTO public.users VALUES (3, TRUE, 12, 'email@email.com', 'firstname', 3, 'lastname', 'password', 'test3');
INSERT INTO public.users VALUES (4, TRUE, 13, 'email@email.com', 'firstname', 4, 'lastname', 'password', 'test4');

-- Prefill public.answers table (Id corresponds to each test user)
-- COLUMN LAYOUT: answer_id, answer1, answer2, answer3, answer4, user_id
INSERT INTO public.answers VALUES (10, 4, 5, 6, 7, 1);
INSERT INTO public.answers VALUES (11, 4, 5, 6, 7, 2);
INSERT INTO public.answers VALUES (12, 4, 5, 6, 7, 3);
INSERT INTO public.answers VALUES (13, 4, 5, 6, 7, 4);
*/
-- Prefill public.conversations table
-- COLUMN LAYOUT: conversation_id, reciever_id, user_id
INSERT INTO public.conversations VALUES (1, 2, 1);
/*
INSERT INTO public.conversations VALUES (2, 2, 3);
INSERT INTO public.conversations VALUES (3, 3, 4);
INSERT INTO public.conversations VALUES (4, 1, 4);
*/
-- Prefill public.message table
-- COLUMN LAYOUT: message_id, conversation_id, message, sender_name, timestamp
INSERT INTO public.message VALUES (10, 1, 'message between user1 and user2 sent from user1', 'user1', '2020-03-27 08:22:01');
INSERT INTO public.message VALUES (11, 1, 'message between user1 and user2 sent from user2', 'user2', '2020-03-27 08:25:01');
/*
INSERT INTO public.message VALUES (12, 2, 'message between user2 and user3 sent from user2', 2, '2020-03-27 08:22:01');
INSERT INTO public.message VALUES (13, 2, 'message between user2 and user3 sent from user3', 3, '2020-03-27 08:25:01');
INSERT INTO public.message VALUES (14, 3, 'message between user3 and user4 sent from user3', 3, '2020-03-27 08:22:01');
INSERT INTO public.message VALUES (15, 3, 'message between user3 and user4 sent from user4', 4, '2020-03-27 08:25:01');
INSERT INTO public.message VALUES (16, 4, 'message between user1 and user4 sent from user1', 1, '2020-03-27 08:22:01');
INSERT INTO public.message VALUES (17, 4, 'message between user1 and user4 sent from user4', 4, '2020-03-27 08:25:01');

-- Prefill question table
-- COLUMN LAYOUT: question_id, max_range, min_range, question_number, question_text
INSERT INTO public.questions VALUES (10, 10, 1, 1, 'This is question number 1');
INSERT INTO public.questions VALUES (11, 10, 1, 2, 'This is question number 2');
INSERT INTO public.questions VALUES (12, 10, 1, 3, 'This is question number 3');
INSERT INTO public.questions VALUES (13, 10, 1, 4, 'This is question number 4');

 */