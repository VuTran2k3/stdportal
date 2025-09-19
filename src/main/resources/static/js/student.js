// const API_URL = "/api/students";
//
// async function loadStudents() {
//     const resp = await fetch(API_URL);
//     const data = await resp.json();
//
//     const tbody = document.querySelector("#studentTable tbody");
//     tbody.innerHTML = "";
//     data.content.forEach(s => {
//         const row = `<tr>
//       <td>${s.id}</td>
//       <td>${s.fullName}</td>
//       <td>${s.email}</td>
//       <td>${s.major ?? ""}</td>
//       <td>${s.gpa ?? ""}</td>
//       <td>
//         <button onclick="editStudent(${s.id})">Edit</button>
//         <button onclick="deleteStudent(${s.id})">Delete</button>
//       </td>
//     </tr>`;
//         tbody.innerHTML += row;
//     });
// }
//
// async function addStudent(event) {
//     event.preventDefault();
//     const student = {
//         name: document.getElementById("fullName").value,
//         dob: document.getElementById("dob").value,
//         gpa: parseFloat(document.getElementById("gpa").value)
//     };
//
//     await fetch(API_URL, {
//         method: "POST",
//         headers: {"Content-Type": "application/json"},
//         body: JSON.stringify(student)
//     });
//
//     document.getElementById("studentForm").reset();
//     loadStudents();
// }
//
// async function deleteStudent(id) {
//     await fetch(`${API_URL}/${id}`, { method: "DELETE" });
//     loadStudents();
// }
//
// async function editStudent(id) {
//     const resp = await fetch(`${API_URL}/${id}`);
//     const s = await resp.json();
//
//     document.getElementById("studentId").value = s.id;
//     document.getElementById("fullName").value = s.name;
//     document.getElementById("dob").value = s.dob ?? "";
//     document.getElementById("gpa").value = s.gpa ?? "";
// }
//
// async function updateStudent(event) {
//     event.preventDefault();
//     const id = document.getElementById("studentId").value;
//     if (!id) return addStudent(event);
//
//     const student = {
//         fullName: document.getElementById("fullName").value,
//         email: document.getElementById("email").value,
//         dob: document.getElementById("dob").value,
//         major: document.getElementById("major").value,
//         gpa: parseFloat(document.getElementById("gpa").value)
//     };
//
//     await fetch(`${API_URL}/${id}`, {
//         method: "PUT",
//         headers: {"Content-Type": "application/json"},
//         body: JSON.stringify(student)
//     });
//
//     document.getElementById("studentForm").reset();
//     document.getElementById("studentId").value = "";
//     loadStudents();
// }
//
// document.addEventListener("DOMContentLoaded", loadStudents);
