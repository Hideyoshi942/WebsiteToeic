import { createRouter, createWebHistory } from 'vue-router';
import MainLayout from '../components/MainLayout.vue';
import HomeView from '../views/HomeView.vue';
import AboutView from '../views/AboutView.vue';
import CourseView from '@/views/CourseView.vue';
import ExamView from '@/views/ExamView.vue';
import TestLayout from '@/components/TestLayout.vue';
import SelectPastExam from '@/views/SelectPastExam.vue';
import TestExam from '@/views/TestExam.vue';
import ExamDetailView from '@/views/ExamDetailView.vue';
import BaseView from '@/views/BaseView.vue';
import NewsView from '@/views/NewsView.vue';
import AdminHomePage from "@/views/admin/AdminHomePage.vue";
import ProfileUser from "@/views/admin/ProfileUser.vue";
import VocabularyManagement from "@/views/admin/VocabularyManagement.vue";
import ListeningManagement from "@/views/admin/ListeningManagement.vue";
import ReadingManagement from "@/views/admin/ReadingManagement.vue";
import PracticeTestManagement from "@/views/admin/PracticeTestManagement.vue";
import GrammarManagement from "@/views/admin/GrammarManagement.vue";
import AccountManagement from "@/views/admin/AccountManagement.vue";
import LoginView from "@/views/LoginView.vue";
import HomePage from "@/views/HomePage.vue";
import ForgetPassword from "@/views/ForgetPassword.vue";
import GrammarLearn from "@/views/learningtoeic/GrammarLearn.vue";
import VocabularyLearn from "@/views/learningtoeic/VocabularyLearn.vue";
import VocabularyTest from "@/views/examtoeic/VocabularyTest.vue";
import GrammarTest from "@/views/examtoeic/GrammarTest.vue";
import ReadingTest from "@/views/examtoeic/ReadingTest.vue";
import ListeningTest from "@/views/examtoeic/ListeningTest.vue";
import PracticeTestToeic from "@/views/examtoeic/PracticeTestToeic.vue";
import VocabularyLessonContent
  from "@/views/learningtoeic/VocabularyLessonContent.vue";
import InfoPerson from "@/views/InfoPerson.vue";
import GrammarLessonContent
  from "@/views/learningtoeic/GrammarLessonContent.vue";
import ListVocabularyTest from "@/views/examtoeic/ListVocabularyTest.vue";
import ListGrammarTest from "@/views/examtoeic/ListGrammarTest.vue";
import ListReadingTest from "@/views/examtoeic/ListReadingTest.vue";
import ListListeningTest from "@/views/examtoeic/ListListeningTest.vue";
import ListPracticeTest from "@/views/examtoeic/ListPracticeTest.vue";
import ResultTest from "@/views/ResultTest.vue";
import PostNews from "@/views/admin/PostNews.vue";
import NewsDetails from "@/views/NewsDetails.vue";
import DetailResultTest from "@/views/DetailResultTest.vue";

const routes = [
  {
    path: '/',
    component: MainLayout, 
    children: [
      { path: '', name: 'Home', component: HomeView},
      { path: 'about', name: 'About', component: AboutView},
      { path: 'login', name: 'LoginView', component: LoginView},
      { path: 'course', name: 'Course', component: CourseView},
      { path: 'exams', name: 'Exams', component: ExamView},
      { path: 'exam-detail', name: 'ExamDetail', component: ExamDetailView},
      { path: 'base', name: 'Base', component: BaseView},
      { path: 'news', name: 'News', component: NewsView, props: true},
      { path: 'newsdetails/:id', name: 'NewsDetails', component: NewsDetails, props: true},
      { path: 'homepage', name: 'HomePage', component: HomePage},
      { path: 'forgetpassword', name: 'ForgetPassword', component: ForgetPassword},

    ],
  },
  {
    path: '/',
    children: [
      { path: 'vocabularylearn', name: 'VocabularyLearn', component: VocabularyLearn, meta: {requiresAuth: true, role: 2}},
      { path: 'grammarlearn', name: 'GrammarLearn', component: GrammarLearn, meta: {requiresAuth: true, role: 2}},
      { path: 'vocabularylessoncontent/:id', name: 'VocabularyLessonContent', component: VocabularyLessonContent, meta: {requiresAuth: true, role: 2}, props: true, },
      { path: 'grammarlessoncontent/:id', name: 'GrammarLessonContent', component: GrammarLessonContent, meta: {requiresAuth: true, role: 2}, props: true, },
    ]
  },
  {
    path: '/',
    children: [
      { path: 'infoperson', name: 'InfoPerson', component: InfoPerson, meta: {requiresAuth: true, role: 2}},
      { path: 'resulttest', name: 'ResultTest', component: ResultTest, meta: {requiresAuth: true, role: 2}},
      { path: 'detailresulttest/:id', name: 'DetailResultTest', component: DetailResultTest, meta: {requiresAuth: true, role: 2, props: true}},
    ]
  },
  {
    path: '/',
    component: TestLayout, 
    children: [
      { path: 'vocabularytest/:id', name: 'VocabularyTest', component: VocabularyTest, meta: {requiresAuth: true, role: 2}, props: true,},
      { path: 'listvocabularytest', name: 'ListVocabularyTest', component: ListVocabularyTest, meta: {requiresAuth: true, role: 2}},
      { path: 'grammartest/:id', name: 'GrammarTest', component: GrammarTest, meta: {requiresAuth: true, role: 2}, props: true,},
      { path: 'listgrammartest', name: 'ListGrammarTest', component: ListGrammarTest, meta: {requiresAuth: true, role: 2}},
      { path: 'readingtest/:id', name: 'ReadingTest', component: ReadingTest, meta: {requiresAuth: true, role: 2}, props: true,},
      { path: 'listreadingtest', name: 'ListReadingTest', component: ListReadingTest, meta: {requiresAuth: true, role: 2}},
      { path: 'listeningtest/:id', name: 'ListeningTest', component: ListeningTest, meta: {requiresAuth: true, role: 2}, props: true,},
      { path: 'listlisteningtest', name: 'ListListeningTest', component: ListListeningTest, meta: {requiresAuth: true, role: 2}},
      { path: 'practicetesttoeic/:id', name: 'PracticeTestToeic', component: PracticeTestToeic, meta: {requiresAuth: true, role: 2}, props: true,},
      { path: 'listpracticetest', name: 'ListPracticeTest', component: ListPracticeTest, meta: {requiresAuth: true, role: 2}},
    ]
  },
  {
    path: '/test-online',
    component: TestLayout,
    children: [
      { path: '', name: 'TestOnline', component: SelectPastExam, meta: {requiresAuth: true, role: 2}},
      { path: 'test', name: 'TestExam', component: TestExam, meta: {requiresAuth: true, role: 2}}
    ]
  },
  {
    path: '/admin',
    component: AdminHomePage,
    children: [
      {path: 'postnews', name: "PostNews", component: PostNews, meta: {requiresAuth: true, role: 1}},
      {path: 'listeningmanagement', name: "ListeningManagement", component: ListeningManagement, meta: {requiresAuth: true, role: 1}},
      {path: 'readingmanagement', name: "ReadingManagement", component: ReadingManagement, meta: {requiresAuth: true, role: 1}},
      {path: 'practicetestmanagement', name: "PracticeTestManagement", component: PracticeTestManagement, meta: {requiresAuth: true, role: 1}},
      {path: 'grammarmanagement', name: "GrammarManagement", component: GrammarManagement, meta: {requiresAuth: true, role: 1}},
      {path: 'vocabularymanagement', name: "VocabularyManagement", component: VocabularyManagement, meta: {requiresAuth: true, role: 1}},
      {path: 'accountmanagement', name: "AccountManagement", component: AccountManagement, meta: {requiresAuth: true, role: 1}},
      {path: 'profile', name: "ProfileUser", component: ProfileUser, meta: {requiresAuth: true, role: 1}},
    ],
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes,
  scrollBehavior(to, from, savedPosition) {
    if (savedPosition) {
      return savedPosition;
    } else {
      return { top: 0 };
    }
  }
});

router.beforeEach((to, from, next) => {
  const usertoeic = JSON.parse(localStorage.getItem('usertoeic'));
  const isAuthenticated = !!usertoeic;
  const userRole = usertoeic ? usertoeic.role : null;

  if (to.meta.requiresAuth && !isAuthenticated) {
    next({ name: 'LoginView' });
  } else if (to.meta.requiresAuth && userRole !== to.meta.role) {
    alert("Bạn không có quyền truy cập trang này.");
    next(false);
  } else {
    next();
  }
});

export default router;
